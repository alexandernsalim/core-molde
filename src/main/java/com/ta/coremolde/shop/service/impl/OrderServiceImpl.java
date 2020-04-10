package com.ta.coremolde.shop.service.impl;

import com.ta.coremolde.shop.model.entity.Order;
import com.ta.coremolde.shop.repository.OrderRepository;
import com.ta.coremolde.shop.service.OrderService;
import com.ta.coremolde.shop.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {

    private static final String ORD_PREFIX = "ORD";

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ShipmentService shipmentService;

    @Override
    public Order createOrder() {

        return null;
    }

    private String generateTransactionNo() {
        long timestamp = new Date().getTime();

        return ORD_PREFIX + timestamp;
    }

}
