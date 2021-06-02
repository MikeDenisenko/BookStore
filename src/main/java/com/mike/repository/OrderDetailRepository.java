package com.mike.repository;

import com.mike.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

    @Query("select o from OrderDetail o where o.order.id = :id")
    List<OrderDetail> findByOrderId(@Param("id") int id);

    @Query("select o from OrderDetail o where o.book.id = :id")
    List<OrderDetail> findByBookId(@Param("id") int id);
}
