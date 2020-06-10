package com.ta.coremolde.master.service;

import com.ta.coremolde.master.model.request.AddressRequest;
import com.ta.coremolde.master.model.response.AddressResponse;
import com.ta.coremolde.shop.model.response.ShipmentAddressResponse;

import java.util.List;

public interface AddressService {

    List<AddressResponse> getAllAddress(String email);

    ShipmentAddressResponse getShipmentAddress(String email, Integer addressId);

    AddressResponse getAddress(Integer addressId);

    AddressResponse addAddress(String email, AddressRequest addressRequest);

    AddressResponse updateAddress(Integer addressId, AddressRequest addressRequest);

    String removeAddress(Integer addressId);

    AddressResponse setAsPrimary(String email, Integer addressId);

}
