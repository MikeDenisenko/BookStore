package com.mike.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Book} class represents model of book.
 *
 * @version 1.0
 * @author Mikhail Denisenko
 */
@Entity
@Table(name = "books")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int quantityLeftInStore;

    @Column(nullable = false)
    private BigDecimal actualPrice;

    private String photoPath = "";

    @Column(nullable = false)
    private boolean visibility;

    @OneToMany(mappedBy = "book",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<OrderDetail> orderDetails = new ArrayList<>();

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String path) {
        this.photoPath = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantityLeftInStore() {
        return quantityLeftInStore;
    }

    public void setQuantityLeftInStore(int counter) {
        this.quantityLeftInStore = counter;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal currentPrice) {
        this.actualPrice = currentPrice;
    }

    public boolean getVisibility() {
        return visibility;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }
}
