package com.ta.coremolde.shop.service;

import com.ta.coremolde.shop.model.entity.Shipment;

public interface ShipmentService {

    Shipment createShipment(String courier, String address, int originId, String originCity, int destinationId, String destinationCity, long totalShipmentPrice);
    Shipment updateShipment(Integer shipmentId, String airwayBill);

}
