package com.ta.coremolde.master.controller;

import com.ta.coremolde.master.model.constant.PathConstant;
import com.ta.coremolde.master.model.constant.StatusConstant;
import com.ta.coremolde.master.model.entity.Request;
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

    @PutMapping(PathConstant.REJECT_REQUEST)
    public Response<String> cancelRequest(@PathVariable Integer id) {
        return toResponse(requestService.changeRequestStatus(id, StatusConstant.REJECT));
    }

}
