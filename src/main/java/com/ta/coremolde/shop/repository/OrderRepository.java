package com.ta.coremolde.shop.repository;

import com.ta.coremolde.shop.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
