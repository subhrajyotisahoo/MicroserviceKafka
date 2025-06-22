package com.microservice.stockservice.service;

import com.microservice.stockservice.dto.Order;
import com.microservice.stockservice.entity.OrderEntity;
import com.microservice.stockservice.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
    @Autowired
    private OrderRepository orderRepository;

    public OrderEntity saveOrder(Order order) {
        OrderEntity entity = new OrderEntity();
        entity.setOrderId(order.getOrderId());
        entity.setName(order.getName());
        entity.setQty(order.getQty());
        entity.setPrice(order.getPrice());
        LOGGER.info("Saving order to DB: {}", entity);
        return orderRepository.save(entity);
    }

    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    @Cacheable(value = "orders", key = "#id")
    public OrderEntity getOrderById(String id) {
        LOGGER.info("Fetching order by ID {} from DB", id);
        return orderRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.warn("Order with ID {} does not exist", id);
                    return new RuntimeException("Order not exists");
                });
    }

    @CacheEvict(value = "orders", key = "#id")
    public void deleteOrder(String id) {
        LOGGER.info("Deleting order with ID: {}", id);
        orderRepository.deleteById(id);
    }
}
