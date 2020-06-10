package com.ta.coremolde.shop.repository;

import com.ta.coremolde.shop.model.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment, Integer> {

    Shipment findShipmentById(Integer id);

}
