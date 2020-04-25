package com.ta.coremolde.shop.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shipment", schema = "public")
public class Shipment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "airway_bill")
    private String airwayBill;

    @Column(name = "courier")
    private String courier;

    @Column(name = "address")
    private String address;

    @Column(name = "origin_id")
    private int originId;

    @Column(name = "origin_city")
    private String originCity;

    @Column(name = "destination_id")
    private int destinationId;

    @Column(name = "destination_city")
    private String destinationCity;

    @Column(name = "total_shipment_price")
    private long totalShipmentPrice;

}
