package com.ta.coremolde.shop.model.rajaongkir;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourierServices {

    private String service;
    private String description;
    private List<CostItem> cost;

}
