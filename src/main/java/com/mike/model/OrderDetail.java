package com.mike.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The {@code OrderDetail} class represents model of order detail.
 *
 * @version 1.0
 * @author Mikhail Denisenko
 */
@Entity
@Table(name = "orderDetails")
public class OrderDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private int quantityInOrder;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bookId")
    private Book book;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orderId")
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantityInOrder() {
        return quantityInOrder;
    }

    public void setQuantityInOrder(int quantityInOrder) {
        this.quantityInOrder = quantityInOrder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
