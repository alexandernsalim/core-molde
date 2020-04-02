package com.ta.coremolde.master.service;

import com.ta.coremolde.master.model.constant.StatusConstant;
import com.ta.coremolde.master.model.request.RequestRequest;

public interface RequestService {

    String changeRequestStatus(Integer id, StatusConstant condition);

    String createRequest(String email, RequestRequest requestRequest);

    String updateRequest(String email, Integer id, RequestRequest requestRequest);

    String cancelRequest(String email, Integer id);

}
