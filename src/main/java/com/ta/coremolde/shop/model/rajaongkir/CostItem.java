package com.ta.coremolde.shop.model.rajaongkir;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CostItem {

    private long value;
    private String etd;
    private String note;

}
