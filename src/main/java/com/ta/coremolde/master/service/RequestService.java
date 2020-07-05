package com.ta.coremolde.master.service;

import com.ta.coremolde.master.model.constant.StatusConstant;
import com.ta.coremolde.master.model.request.RequestRequest;
import com.ta.coremolde.master.model.response.RequestResponse;

import java.util.List;

public interface RequestService {

    List<RequestResponse> getAllRequest(Integer id);

    String changeRequestStatus(Integer id, StatusConstant condition);

    String createRequest(String email, RequestRequest requestRequest);

    String updateRequest(String email, Integer id, RequestRequest requestRequest);

    String cancelRequest(String email, Integer id);

    RequestResponse getActiveRequest(String email);
}
