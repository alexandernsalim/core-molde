package com.ta.coremolde.master.controller;


import com.ta.coremolde.master.model.request.AddressRequest;
import com.ta.coremolde.master.model.response.AddressResponse;
import com.ta.coremolde.master.model.response.Response;
import com.ta.coremolde.master.service.AddressService;
import com.ta.coremolde.shop.model.response.ShipmentAddressResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("molde/api/v1/address")
public class AddressController extends GlobalController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/all")
    public Response<List<AddressResponse>> getAllAddress(HttpServletRequest httpServletRequest) {
        String email = httpServletRequest.getUserPrincipal().getName();

        return toResponse(addressService.getAllAddress(email));
    }

    @GetMapping("/shipment")
    public Response<ShipmentAddressResponse> getShipmentAddress(@RequestParam(required = false) Integer addressId, HttpServletRequest httpServletRequest) {
        String email = httpServletRequest.getUserPrincipal().getName();

        return toResponse(addressService.getShipmentAddress(email, addressId));
    }

    @GetMapping("/{addressId}/view")
    public Response<AddressResponse> getAddress(@PathVariable("addressId") Integer addressId) {
        return toResponse(addressService.getAddress(addressId));
    }

    @PostMapping("/add")
    public Response<AddressResponse> addAddress(@ModelAttribute AddressRequest addressRequest, HttpServletRequest httpServletRequest) {
        String email = httpServletRequest.getUserPrincipal().getName();

        return toResponse(addressService.addAddress(email, addressRequest));
    }

    @PutMapping("/{addressId}/update")
    public Response<AddressResponse> updateAddress(@PathVariable("addressId") Integer addressId, @ModelAttribute AddressRequest addressRequest) {
        return toResponse(addressService.updateAddress(addressId, addressRequest));
    }

    @PutMapping("/{addressId}/primary")
    public Response<AddressResponse> setAsPrimary(@PathVariable("addressId") Integer addressId, HttpServletRequest httpServletRequest) {
        String email = httpServletRequest.getUserPrincipal().getName();

        return toResponse(addressService.setAsPrimary(email, addressId));
    }

    @DeleteMapping("/{addressId}/remove")
    public Response<String> removeAddress(@PathVariable("addressId") Integer addressId) {
        return toResponse(addressService.removeAddress(addressId));
    }

}
