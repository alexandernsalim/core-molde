package com.ta.coremolde.shop.service.impl;

import com.ta.coremolde.shop.model.entity.CartItem;
import com.ta.coremolde.shop.model.entity.Order;
import com.ta.coremolde.shop.model.entity.OrderItem;
import com.ta.coremolde.shop.model.entity.Product;
import com.ta.coremolde.shop.repository.OrderItemRepository;
import com.ta.coremolde.shop.service.OrderItemService;
import com.ta.coremolde.shop.service.ProductService;
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
    @Transactional("shopTransactionManager")
    public void createOrderItem(Order order, List<CartItem> items) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem item : items) {
            int qty = item.getQty();
            Product product = productService.decreaseStock(item.getProduct(), qty);

            orderItems.add(OrderItem.builder()
                    .product(product)
                    .qty(qty)
                    .totalWeight(item.getTotalWeight())
                    .totalPrice(item.getTotalPrice())
                    .order(order)
                    .build());
        }

        orderItemRepository.saveAll(orderItems);
    }

}
