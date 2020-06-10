package com.ta.coremolde.master.repository;

import com.ta.coremolde.master.model.entity.Address;
import com.ta.coremolde.master.model.entity.ShopUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    boolean existsAddressByShopUserAndPrimaryAddressEquals(ShopUser shopUser, boolean primary);

    boolean existsAddressByShopUser(ShopUser shopUser);

    List<Address> findAllByShopUser(ShopUser shopUser);

    Address findAddressById(Integer addressId);

    Address findAddressByShopUserAndPrimaryAddressEquals(ShopUser shopUser, boolean primary);

}
