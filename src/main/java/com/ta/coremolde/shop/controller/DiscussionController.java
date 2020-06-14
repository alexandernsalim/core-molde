package com.ta.coremolde.shop.controller;

import com.ta.coremolde.master.controller.GlobalController;
import com.ta.coremolde.master.model.constant.PathConstant;
import com.ta.coremolde.master.model.response.Response;
import com.ta.coremolde.shop.model.request.DiscussionRequest;
import com.ta.coremolde.shop.model.response.DiscussionDetailResponse;
import com.ta.coremolde.shop.model.response.DiscussionResponseServiceResponse;
import com.ta.coremolde.shop.model.response.DiscussionServiceResponse;
import com.ta.coremolde.shop.service.DiscussionService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(PathConstant.PREFIX + "discussions")
public class DiscussionController extends GlobalController {

    @Autowired
    private DiscussionService discussionService;

    @GetMapping
    public Response<List<DiscussionServiceResponse>> getDiscussions() {
        return toResponse(discussionService.getAllDiscussions());
    }

    @GetMapping("/{discussionId}")
    public Response<DiscussionDetailResponse> getDiscussion(@PathVariable Integer discussionId) {
        return toResponse(discussionService.getDiscussion(discussionId));
    }

    @PostMapping("/{productId}")
    public Response<DiscussionServiceResponse> createDiscussion(@PathVariable Integer productId, @ModelAttribute DiscussionRequest request, HttpServletRequest httpServletRequest) {
        String email = httpServletRequest.getUserPrincipal().getName();

        return toResponse(discussionService.createDiscussion(request, productId, email));
    }

    @MessageMapping("/{discussionId}/reply")
    @SendTo("/topic/discussion")
    public DiscussionResponseServiceResponse replyDiscussion(@DestinationVariable Integer discussionId, @Payload DiscussionRequest request, MessageHeaders messageHeaders) {
        MultiValueMap<String, String> multiValueMap = messageHeaders.get(StompHeaderAccessor.NATIVE_HEADERS, MultiValueMap.class);
        for (Map.Entry<String, List<String>> head : multiValueMap.entrySet()) {
            String key = head.getKey();
            String value = head.getValue().get(0);
            if (key.equals("Authorization")) {
                authenticateJWT(value);
            }
        }

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return discussionService.replyDiscussion(discussionId, request, email);
    }

    private void authenticateJWT(String req) {
        String token = req.replace("Bearer ", "");

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey("tccoursesecret".getBytes())
                    .parseClaimsJws(token)
                    .getBody();
            String username = claims.getSubject();

            if (username != null) {
                List<String> authorities = (List<String>) claims.get("authorities");
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        username, null, authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
        }
    }

}
