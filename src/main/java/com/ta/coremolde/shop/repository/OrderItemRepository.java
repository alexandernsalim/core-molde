package com.ta.coremolde.shop.repository;

import com.ta.coremolde.shop.model.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
