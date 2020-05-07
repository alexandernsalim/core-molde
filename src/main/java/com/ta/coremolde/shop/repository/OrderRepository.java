package com.ta.coremolde.shop.repository;

import com.ta.coremolde.shop.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    Order findOrderByIdEquals(Integer orderId);

    List<Order> findAllByShopUserIdEqualsOrderByIdDesc(Integer shopUserId);

    List<Order> findAllByOrderByIdDesc();

}
