package com.ta.coremolde.master.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateShopRequest {
    private String province;
    private String provinceId;
    private String city;
    private String cityId;
    private String street;
    private String postalCode;
}
