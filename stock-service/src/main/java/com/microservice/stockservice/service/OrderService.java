package com.microservice.stockservice.service;

import com.microservice.stockservice.dto.Order;
import com.microservice.stockservice.entity.OrderEntity;
import com.microservice.stockservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderEntity saveOrder(Order order) {
        OrderEntity entity = new OrderEntity();
        entity.setOrderId(order.getOrderId());
        entity.setName(order.getName());
        entity.setQty(order.getQty());
        entity.setPrice(order.getPrice());
        return orderRepository.save(entity);
    }

    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    public OrderEntity getOrderById(String id) {
        return orderRepository.findById(id).orElse(null);
    }

    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }
}
