package com.ta.coremolde.master.controller;

import com.ta.coremolde.master.model.constant.PathConstant;
import com.ta.coremolde.master.model.constant.StatusConstant;
import com.ta.coremolde.master.model.request.RequestRequest;
import com.ta.coremolde.master.model.response.RequestResponse;
import com.ta.coremolde.master.model.response.Response;
import com.ta.coremolde.master.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(PathConstant.REQUEST_MAPPING)
public class RequestController extends GlobalController {

    @Autowired
    private RequestService requestService;

    @GetMapping("/get")
    public Response<List<RequestResponse>> getAllRequest(@RequestParam(required = false) Integer status) {
        int reqStatus = (status != null) ? status : 0;

        return toResponse(requestService.getAllRequest(reqStatus));
    }

    @GetMapping("/get/active")
    public Response<List<RequestResponse>> getAllActiveRequest() {
        return toResponse(requestService.getAllActiveRequest());
    }

    @GetMapping("/active")
    public Response<RequestResponse> hasActiveRequest(HttpServletRequest httpServletRequest) {
        String email = httpServletRequest.getUserPrincipal().getName();

        return toResponse(requestService.getActiveRequest(email));
    }

    @GetMapping("/get/shop")
    public Response<RequestResponse> getShopRequest(HttpServletRequest httpServletRequest) {
        String email = httpServletRequest.getUserPrincipal().getName();

        return toResponse(requestService.getShopRequest(email));
    }

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

    @PutMapping(PathConstant.ACCEPT_REQUEST)
    public Response<String> acceptRequest(@PathVariable Integer id) {
        return toResponse(requestService.changeRequestStatus(id, StatusConstant.ACCEPT));
    }

    @PutMapping(PathConstant.COMPLETE_REQUEST)
    public Response<String> completeRequest(@PathVariable Integer id, @RequestParam("url") String url) {
        return toResponse(requestService.completeRequest(id, url));
    }

    @PutMapping(PathConstant.REJECT_REQUEST)
    public Response<String> cancelRequest(@PathVariable Integer id) {
        return toResponse(requestService.changeRequestStatus(id, StatusConstant.REJECT));
    }

}
