package com.ta.coremolde.shop.service;

import com.ta.coremolde.shop.model.entity.Shipment;

public interface ShipmentService {

    Shipment createShipment(float totalWeight, String courier, String address, int originId, String originCity, int destinationId, String destinationCity);
    Shipment updateShipment(Integer shipmentId, String airwayBill);

}
