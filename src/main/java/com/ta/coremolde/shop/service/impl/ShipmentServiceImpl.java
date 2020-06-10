package com.ta.coremolde.shop.service.impl;

import com.ta.coremolde.shop.model.entity.Shipment;
import com.ta.coremolde.shop.repository.ShipmentRepository;
import com.ta.coremolde.shop.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Override
    public Shipment createShipment(
            String courier,
            String address,
            int originId, String originCity,
            int destinationId,
            String destinationCity,
            long totalShipmentPrice,
            String recipient,
            String recipientPhone) {
        Shipment shipment = Shipment.builder()
                .courier(courier)
                .address(address)
                .originId(originId)
                .originCity(originCity)
                .destinationId(destinationId)
                .destinationCity(destinationCity)
                .totalShipmentPrice(totalShipmentPrice)
                .recipient(recipient)
                .recipientPhone(recipientPhone)
                .build();

        return shipmentRepository.save(shipment);
    }

    @Override
    public Shipment updateShipment(Integer shipmentId, String airwayBill) {
        Shipment shipment = shipmentRepository.findShipmentById(shipmentId);
        shipment.setAirwayBill(airwayBill);

        return shipmentRepository.save(shipment);
    }

}
