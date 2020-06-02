package com.ta.coremolde.shop.service.impl;

import com.ta.coremolde.shop.model.entity.CartItem;
import com.ta.coremolde.shop.model.entity.Order;
import com.ta.coremolde.shop.model.entity.OrderItem;
import com.ta.coremolde.shop.model.entity.Product;
import com.ta.coremolde.shop.model.response.OrderItemResponse;
import com.ta.coremolde.shop.repository.OrderItemRepository;
import com.ta.coremolde.shop.service.OrderItemService;
import com.ta.coremolde.shop.service.ProductService;
import com.ta.coremolde.util.ResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductService productService;

    @Override
    public List<OrderItemResponse> getOrderItems(Integer orderId) {
        return ResponseMapper.mapAsList(orderItemRepository.getAllByOrder_Id(orderId), OrderItemResponse.class);
    }

    @Override
    @Transactional("shopTransactionManager")
    public void createOrderItem(Order order, List<CartItem> items) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem item : items) {
            int qty = item.getQty();
            Product product = productService.updateStock(item.getProduct(), qty);

            orderItems.add(OrderItem.builder()
                    .product(product)
                    .qty(qty)
                    .totalWeight(item.getTotalWeight())
                    .totalPrice(item.getTotalPrice())
                    .order(order)
                    .reviewed(0)
                    .build());
        }

        orderItemRepository.saveAll(orderItems);
    }

    @Override
    @Transactional("shopTransactionManager")
    public void updateReviewedStatus(Integer orderItemId) {
        OrderItem orderItem = orderItemRepository.findOrderItemById(orderItemId);
        orderItem.setReviewed(1);
        orderItemRepository.save(orderItem);
    }

    @Override
    @Transactional("shopTransactionManager")
    public void removeOrderItem(Integer orderItemId) {
        orderItemRepository.deleteById(orderItemId);
    }

}
