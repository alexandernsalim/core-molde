package com.ta.coremolde.shop.service.impl;

import com.ta.coremolde.master.model.entity.ShopUser;
import com.ta.coremolde.master.service.ShopUserService;
import com.ta.coremolde.shop.model.constant.OrderStatusConstant;
import com.ta.coremolde.shop.model.entity.CartItem;
import com.ta.coremolde.shop.model.entity.Order;
import com.ta.coremolde.shop.model.entity.Product;
import com.ta.coremolde.shop.model.entity.Shipment;
import com.ta.coremolde.shop.model.request.OrderRequest;
import com.ta.coremolde.shop.model.response.OrderItemResponse;
import com.ta.coremolde.shop.model.response.OrderResponse;
import com.ta.coremolde.shop.repository.OrderRepository;
import com.ta.coremolde.shop.service.*;
import com.ta.coremolde.util.ResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.ArrayList;
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

    @Autowired
    private ShopUserService shopUserService;

    @Autowired
    private ProductService productService;

    @Autowired
    private StorageService storageService;

    @Override
    public List<Order> getShopOrder() {
        return orderRepository.findAllByOrderByIdDesc();
    }

    @Override
    public List<OrderResponse> getUserOrder(String email) {
        List<OrderResponse> resultOrders = new ArrayList<>();
        ShopUser shopUser = shopUserService.getShopUser(email);
        List<Order> orders = orderRepository.findAllByShopUserIdEqualsOrderByIdDesc(shopUser.getId());

        for (Order order : orders) {
            List<OrderItemResponse> items = orderItemService.getOrderItems(order.getId());

            resultOrders.add(OrderResponse.builder()
                    .id(order.getId())
                    .transactionNo(order.getTransactionNo())
                    .transactionDate(order.getTransactionDate())
                    .items(items)
                    .totalItem(calculateTotalItem(items))
                    .totalPrice(order.getTotalPrice())
                    .totalPaymentPrice(order.getTotalPaymentPrice())
                    .status(order.getStatus())
                    .shipment(order.getShipment())
                    .build());
        }

        return resultOrders;
    }

    @Override
    public OrderResponse getOrderDetail(Integer orderId) {
        return ResponseMapper.map(orderRepository.getOne(orderId), OrderResponse.class);
    }

    @Override
    @Transactional("shopTransactionManager")
    public Order createOrder(Integer shopUserId, List<CartItem> items, OrderRequest orderRequest) {
        long timestamp = new Date().getTime();
        String transactionNo = ORD_PREFIX + timestamp;

        Shipment shipment = shipmentService.createShipment(
                orderRequest.getCourier(),
                orderRequest.getAddress(),
                orderRequest.getOriginId(),
                orderRequest.getOriginCity(),
                orderRequest.getDestinationId(),
                orderRequest.getDestinationCity(),
                orderRequest.getTotalShipmentPrice(),
                orderRequest.getRecipient(),
                orderRequest.getRecipientPhone());
        Order order = Order.builder()
                .transactionNo(transactionNo)
                .transactionDate(new Timestamp(timestamp))
                .totalPrice(orderRequest.getTotalPrice())
                .totalPaymentPrice(orderRequest.getTotalPaymentPrice())
                .shipment(shipment)
                .shopUserId(shopUserId)
                .status(OrderStatusConstant.WAITING_FOR_PAYMENT.getStatus())
                .build();

        orderRepository.save(order);
        orderItemService.createOrderItem(order, items);

        return order;
    }

    @Override
    public Order acceptOrder(Integer orderId) {
        Order order = orderRepository.findOrderByIdEquals(orderId);
        order.setStatus(OrderStatusConstant.PAYMENT_ACCEPTED.getStatus());

        return orderRepository.save(order);
    }

    @Override
    @Transactional("shopTransactionManager")
    public Order cancelOrder(Integer orderId) {
        Order order = orderRepository.findOrderByIdEquals(orderId);
        List<OrderItemResponse> orderItems = orderItemService.getOrderItems(orderId);
        for (OrderItemResponse orderItem : orderItems) {
            Product product = orderItem.getProduct();
            productService.updateStock(product, orderItem.getQty() * -1);
        }
        order.setStatus(OrderStatusConstant.CANCELLED.getStatus());

        return orderRepository.save(order);
    }

    @Override
    public Order uploadPaymentImage(Integer shopId, Integer orderId, MultipartFile paymentImage) {
        Order order = orderRepository.findOrderByIdEquals(orderId);
        String filename = order.getTransactionNo();

        storageService.savePaymentImage(shopId, filename, paymentImage);
        order.setStatus(OrderStatusConstant.WAITING_FOR_PAYMENT_CONFIRMATION.getStatus());
        order.setPaymentImage("/molde/api/v1/storage/payment/shop-" + shopId + "-payment-" + filename);

        return orderRepository.save(order);
    }

    private int calculateTotalItem(List<OrderItemResponse> items) {
        int total = 0;

        for (OrderItemResponse item : items) {
            total += item.getQty();
        }

        return total;
    }

}

