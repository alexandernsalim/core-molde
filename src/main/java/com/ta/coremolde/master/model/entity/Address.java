package com.ta.coremolde.master.model.entity;

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
@Table(name = "address")
public class Address {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "save_as")
    private String saveAs;

    @Column(name = "recipient")
    private String recipient;

    @Column(name = "recipient_phone")
    private String recipientPhone;

    @Column(name = "province_id")
    private String provinceId;

    @Column(name = "province")
    private String province;

    @Column(name = "city_id")
    private String cityId;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "primary_address")
    private Boolean primaryAddress;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "shop_user_id", referencedColumnName = "id")
    private ShopUser shopUser;

}
