package com.ta.coremolde.master.service;

import com.ta.coremolde.master.model.constant.StatusConstant;
import com.ta.coremolde.master.model.request.RequestRequest;
import com.ta.coremolde.master.model.response.RequestResponse;

import java.util.List;

public interface RequestService {

    List<RequestResponse> getAllRequest(Integer id);

    List<RequestResponse> getAllActiveRequest();

    RequestResponse getActiveRequest(String email);

    RequestResponse getShopRequest(String email);

    String changeRequestStatus(Integer id, StatusConstant condition);

    String createRequest(String email, RequestRequest requestRequest);

    String updateRequest(String email, Integer id, RequestRequest requestRequest);

    RequestResponse completeRequest(Integer id, String downloadUrl);

    String cancelRequest(String email, Integer id);
}
