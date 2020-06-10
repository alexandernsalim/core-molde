package com.ta.coremolde.master.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {
    private String saveAs;
    private String recipient;
    private String recipientPhone;
    private String provinceId;
    private String province;
    private String cityId;
    private String city;
    private String street;
    private String postalCode;
    private Boolean primaryAddress;
}
