package com.ta.coremolde.master.controller;

import com.ta.coremolde.master.model.constant.PathConstant;
import com.ta.coremolde.master.model.request.RequestRequest;
import com.ta.coremolde.master.model.response.Response;
import com.ta.coremolde.master.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(PathConstant.REQUEST_MAPPING)
public class RequestController extends GlobalController {

    @Autowired
    private RequestService requestService;

    @PostMapping(PathConstant.CREATE_REQUEST)
    public Response<String> createRequest(@ModelAttribute RequestRequest requestRequest, HttpServletRequest httpServletRequest) {
        String email = httpServletRequest.getUserPrincipal().getName();

        return toResponse(requestService.createRequest(email, requestRequest));
    }

    @PutMapping(PathConstant.UPDATE_REQUEST)
    public Response<String> updateRequest(@PathVariable Integer id, @ModelAttribute RequestRequest requestRequest, HttpServletRequest httpServletRequest) {
        String email = httpServletRequest.getUserPrincipal().getName();

        return toResponse(requestService.updateRequest(email, id, requestRequest));
    }

}
