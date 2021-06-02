package com.mike.service;

import com.mike.model.OrderDetail;
import com.mike.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {

    private OrderDetailRepository orderDetailRepository;

    @Autowired
    public void setOrderDetailRepository(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    public OrderDetail save(OrderDetail orderDetail) {
        return orderDetailRepository.saveAndFlush(orderDetail);
    }

    public void delete(OrderDetail orderDetail) {
        orderDetailRepository.delete(orderDetail);
    }

    public void update(OrderDetail orderDetail) {
        orderDetailRepository.saveAndFlush(orderDetail);
    }

    public List<OrderDetail> findAll() {
        return orderDetailRepository.findAll();
    }

    public List<OrderDetail> findByOrderId(int orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }

    public List<OrderDetail> findByBookId(int id) {
        return orderDetailRepository.findByBookId(id);
    }
}
