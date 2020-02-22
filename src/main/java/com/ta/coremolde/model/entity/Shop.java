package com.ta.coremolde.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shop", schema = "public")
public class Shop {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "province")
    private String province;

    @Column(name = "city")
    private String city;

    @Column(name = "sub_district")
    private String subDistrict;

    @Column(name = "street")
    private String street;

    @Column(name = "postal_code")
    private String postalCode;

}
