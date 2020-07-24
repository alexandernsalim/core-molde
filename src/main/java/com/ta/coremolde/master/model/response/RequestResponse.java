package com.ta.coremolde.master.model.response;

import com.ta.coremolde.master.model.entity.Customization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestResponse {
    private Integer id;
    private String shopName;
    private String appName;
    private int status;
    private AccountResponse account;
    private Customization customization;
    private String downloadUrl;
}
