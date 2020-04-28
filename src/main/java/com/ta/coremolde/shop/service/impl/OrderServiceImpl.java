package com.ta.coremolde.shop.service.impl;

import com.ta.coremolde.shop.model.constant.OrderStatusConstant;
import com.ta.coremolde.shop.model.entity.CartItem;
import com.ta.coremolde.shop.model.entity.Order;
import com.ta.coremolde.shop.model.entity.Shipment;
import com.ta.coremolde.shop.model.request.OrderRequest;
import com.ta.coremolde.shop.repository.OrderRepository;
import com.ta.coremolde.shop.service.OrderItemService;
import com.ta.coremolde.shop.service.OrderService;
import com.ta.coremolde.shop.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private static final String ORD_PREFIX = "ORD";

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ShipmentService shipmentService;

    @Autowired
    private OrderItemService orderItemService;

    @Override
    @Transactional("shopTransactionManager")
    public Order createOrder(Integer shopUserId, List<CartItem> items, OrderRequest orderRequest) {
        Shipment shipment = shipmentService.createShipment(
                orderRequest.getCourier(),
                orderRequest.getAddress(),
                orderRequest.getOriginId(),
                orderRequest.getOriginCity(),
                orderRequest.getDestinationId(),
                orderRequest.getDestinationCity(),
                orderRequest.getTotalShipmentPrice());
        Order order = Order.builder()
                .transactionNo(generateTransactionNo())
                .totalPrice(orderRequest.getTotalPrice())
                .totalPaymentPrice(orderRequest.getTotalPaymentPrice())
                .shipment(shipment)
                .shopUserId(shopUserId)
                .status(OrderStatusConstant.IN_PROGRESS.getStatus())
                .build();

        orderRepository.save(order);
        orderItemService.createOrderItem(order, items);

        return order;
    }

    @Override
    public Order acceptOrder() {
        //TODO Change order status

        return null;
    }

    @Override
    public Order rejectOrder() {
        //TODO Return all order item stock to product current stock

        return null;
    }

    private String generateTransactionNo() {
        long timestamp = new Date().getTime();

        return ORD_PREFIX + timestamp;
    }

}
