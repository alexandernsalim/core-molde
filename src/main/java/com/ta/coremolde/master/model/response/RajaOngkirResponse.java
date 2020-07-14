package com.ta.coremolde.master.model.response;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RajaOngkirResponse<T> {
    @SerializedName("rajaongkir")
    private T data;
}
