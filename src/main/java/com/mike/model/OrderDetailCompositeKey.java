package com.mike.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * The {@code OrderDetailCompositeKey} class represents key value for finding specified {@link OrderDetail} instance.
 *
 * @version 1.0
 * @author Mikhail Denisenko
 */
@Embeddable
public class OrderDetailCompositeKey implements Serializable {

    private Integer orderId;

    private Integer bookId;

    public OrderDetailCompositeKey() {

    }

    public OrderDetailCompositeKey(Integer orderId, Integer bookId) {
        this.orderId = orderId;
        this.bookId = bookId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int companyId) {
        this.bookId = companyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDetailCompositeKey that = (OrderDetailCompositeKey) o;

        if (!orderId.equals(that.orderId)) return false;
        return bookId.equals(that.bookId);
    }

    @Override
    public int hashCode() {
        int result = orderId.hashCode();
        result = 31 * result + bookId.hashCode();
        return result;
    }
}
