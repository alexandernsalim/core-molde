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
@Table(name = "shop_user", schema = "public")
public class ShopUser extends Account {

    @Column(name = "shop_id")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Shop shop;

}
