package com.mike.service;

import com.mike.model.Order;
import com.mike.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order find(int id) {
        return orderRepository.find(id);
    }

    public void save(Order order) {
        orderRepository.saveAndFlush(order);
    }

    public void delete(Order order) {
        orderRepository.delete(order);
    }

    public void delete(int id) {
        orderRepository.deleteById(id);
    }

    public void update(Order order) {
        orderRepository.saveAndFlush(order);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> findByUserId(int id) { return orderRepository.findByUserId(id); }

    public List<Order> findByUserName(String name) { return orderRepository.findByUserName(name); }

    public Order getOpenOrder(String name) { return orderRepository.getOpenOrder(name); }
}
