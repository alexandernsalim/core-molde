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

    @Column(name = "province_id")
    private String provinceId;

    @Column(name = "city_id")
    private String cityId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customization_id", referencedColumnName = "id")
    private Customization customization;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shop_tenant_id", referencedColumnName = "id")
    private ShopTenant shopTenant;

}
