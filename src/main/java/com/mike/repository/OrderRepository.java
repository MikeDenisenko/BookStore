package com.mike.repository;

import com.mike.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("select o from Order o where o.id = :id")
    Order find(@Param("id") int id);

    @Query("select o from Order o where o.user.id = :id")
    List<Order> findByUserId(@Param("id") int id);

    @Query("select o from Order o where o.user.name = :name")
    List<Order> findByUserName(@Param("name") String name);

    @Query("select o from Order o where o.orderStat = 'open' and o.user.name = :name")
    Order getOpenOrder(@Param("name") String name);
}
