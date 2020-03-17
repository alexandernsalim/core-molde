package com.ta.coremolde.service;

import com.ta.coremolde.model.request.RequestRequest;

public interface RequestService {

    String createRequest(String email, RequestRequest requestRequest);
    String updateRequest(String email, Integer id, RequestRequest requestRequest);
    String cancelRequest(String email, Integer id);

}
