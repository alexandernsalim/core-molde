package com.ta.coremolde.shop.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentAddressResponse {
    private Integer id;
    private String recipient;
    private String recipientPhone;
    private String provinceId;
    private String province;
    private String cityId;
    private String city;
    private String street;
    private String postalCode;
    private String origin;
    private String originCity;
}
