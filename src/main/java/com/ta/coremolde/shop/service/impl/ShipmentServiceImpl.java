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
    public Shipment createShipment(String courier, String address, int originId, String originCity, int destinationId, String destinationCity, long totalShipmentPrice) {
        Shipment shipment = Shipment.builder()
                .courier(courier)
                .address(address)
                .originId(originId)
                .originCity(originCity)
                .destinationId(destinationId)
                .destinationCity(destinationCity)
                .totalShipmentPrice(totalShipmentPrice)
                .build();

        return shipmentRepository.save(shipment);
    }

    @Override
    public Shipment updateShipment(Integer shipmentId, String airwayBill) {
        Shipment shipment = shipmentRepository.findShipmentById(shipmentId);
        shipment.setAirwayBill(airwayBill);

        return shipmentRepository.save(shipment);
    }

//    private long calculateTotalShipmentPrice() throws IOException {
//        OkHttpClient client = new OkHttpClient();
//        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
//        RequestBody body = RequestBody.create(mediaType, "origin=501&destination=114&weight=1700&courier=jne");
//        Request request = new Request.Builder()
//                .url("https://api.rajaongkir.com/basic/cost")
//                .post(body)
//                .addHeader("key", "dba85a3bca60bb8ea7cebf2990394bcb")
//                .addHeader("content-type", "application/x-www-form-urlencoded")
//                .build();
//        Response response = client.newCall(request).execute();
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
//        RajaOngkirCostResponse rajaOngkirResponse = new Gson().fromJson(response.body().string(), RajaOngkirCostResponse.class);
//
//
//        return 0;
//    }

}
