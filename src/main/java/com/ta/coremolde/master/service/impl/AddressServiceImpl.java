package com.ta.coremolde.master.service.impl;

import com.ta.coremolde.master.model.entity.Address;
import com.ta.coremolde.master.model.entity.ShopUser;
import com.ta.coremolde.master.model.exception.MoldeException;
import com.ta.coremolde.master.model.request.AddressRequest;
import com.ta.coremolde.master.model.response.AddressResponse;
import com.ta.coremolde.master.model.response.ErrorResponse;
import com.ta.coremolde.master.repository.AddressRepository;
import com.ta.coremolde.master.service.AddressService;
import com.ta.coremolde.master.service.ShopUserService;
import com.ta.coremolde.shop.model.response.ShipmentAddressResponse;
import com.ta.coremolde.util.ResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ShopUserService shopUserService;

    @Override
    public List<AddressResponse> getAllAddress(String email) {
        ShopUser shopUser = shopUserService.getShopUser(email);

        return ResponseMapper.mapAsList(addressRepository.findAllByShopUser(shopUser), AddressResponse.class);
    }

    @Override
    public ShipmentAddressResponse getShipmentAddress(String email, Integer addressId) {
        ShopUser shopUser = shopUserService.getShopUser(email);
        String origin = shopUser.getShop().getCityId();
        String originCity = shopUser.getShop().getCity();
        Address address;
        ShipmentAddressResponse response;

        if (addressId != null) {
            address = addressRepository.findAddressById(addressId);
        } else {
            if (!addressRepository.existsAddressByShopUser(shopUser)) {
                throw new MoldeException(
                    ErrorResponse.ADDRESS_LIST_EMPTY.getCode(),
                    ErrorResponse.ADDRESS_LIST_EMPTY.getMessage()
                );
            } else {
                address = addressRepository.findAddressByShopUserAndPrimaryAddressEquals(shopUser, true);
            }
        }
        response = ResponseMapper.map(address, ShipmentAddressResponse.class);
        response.setOrigin(origin);
        response.setOriginCity(originCity);

        return response;
    }

    @Override
    public AddressResponse getAddress(Integer addressId) {
        return ResponseMapper.map(addressRepository.findAddressById(addressId), AddressResponse.class);
    }

    @Override
    public AddressResponse addAddress(String email, AddressRequest addressRequest) {
        ShopUser shopUser = shopUserService.getShopUser(email);
        boolean primary = !addressRepository.existsAddressByShopUserAndPrimaryAddressEquals(shopUser, true);

        Address address = Address.builder()
                .recipient(addressRequest.getRecipient())
                .recipientPhone(addressRequest.getRecipientPhone())
                .saveAs(addressRequest.getSaveAs())
                .provinceId(addressRequest.getProvinceId())
                .province(addressRequest.getProvince())
                .cityId(addressRequest.getCityId())
                .city(addressRequest.getCity())
                .street(addressRequest.getStreet())
                .postalCode(addressRequest.getPostalCode())
                .primaryAddress(primary)
                .shopUser(shopUser)
                .build();

        return ResponseMapper.map(addressRepository.save(address), AddressResponse.class);
    }

    @Override
    public AddressResponse updateAddress(Integer addressId, AddressRequest addressRequest) {
        Address address = addressRepository.findAddressById(addressId);

        if (address == null) {
            throw new MoldeException(
                    ErrorResponse.RESOURCE_NOT_FOUND.getCode(),
                    ErrorResponse.RESOURCE_NOT_FOUND.getMessage()
            );
        }

        address.setRecipient(addressRequest.getRecipient());
        address.setRecipientPhone(addressRequest.getRecipientPhone());
        address.setSaveAs(addressRequest.getSaveAs());
        address.setProvinceId(addressRequest.getProvinceId());
        address.setProvince(addressRequest.getProvince());
        address.setCityId(addressRequest.getCityId());
        address.setCity(addressRequest.getCity());
        address.setStreet(addressRequest.getStreet());
        address.setPostalCode(addressRequest.getPostalCode());
        address.setPrimaryAddress(addressRequest.getPrimaryAddress());

        return ResponseMapper.map(addressRepository.save(address), AddressResponse.class);
    }

    @Override
    public String removeAddress(Integer addressId) {
        Address address = addressRepository.findAddressById(addressId);

        if (address == null) {
            throw new MoldeException(
                    ErrorResponse.RESOURCE_NOT_FOUND.getCode(),
                    ErrorResponse.RESOURCE_NOT_FOUND.getMessage()
            );
        }
        addressRepository.delete(address);

        return "Address removed successfully";
    }

    @Override
    public AddressResponse setAsPrimary(String email, Integer addressId) {
        ShopUser shopUser = shopUserService.getShopUser(email);
        Address address = addressRepository.findAddressById(addressId);
        Address prevPrimary = addressRepository.findAddressByShopUserAndPrimaryAddressEquals(shopUser, true);

        if (address == null) {
            throw new MoldeException(
                    ErrorResponse.RESOURCE_NOT_FOUND.getCode(),
                    ErrorResponse.RESOURCE_NOT_FOUND.getMessage()
            );
        }

        prevPrimary.setPrimaryAddress(false);
        address.setPrimaryAddress(true);
        addressRepository.save(prevPrimary);

        return ResponseMapper.map(addressRepository.save(address), AddressResponse.class);
    }

}
