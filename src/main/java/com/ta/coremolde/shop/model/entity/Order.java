package com.ta.coremolde.shop.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order", schema = "public")
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "transaction_no")
    private String transactionNo;

    @Column(name = "transaction_date")
    private Timestamp transactionDate;

    @Column(name = "total_price")
    private long totalPrice;

    @Column(name = "total_payment_price")
    private long totalPaymentPrice;

    @Column(name = "status")
    private String status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipment_id", referencedColumnName = "id")
    private Shipment shipment;

    @Column(name = "shop_user_id")
    private Integer shopUserId;

    @Column(name = "payment_image")
    private String paymentImage;

}
