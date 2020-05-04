package com.ta.coremolde.shop.repository;

import com.ta.coremolde.shop.model.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    List<OrderItem> getAllByOrder_Id(Integer orderId);

}
