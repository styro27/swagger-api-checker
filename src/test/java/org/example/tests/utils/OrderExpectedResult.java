package org.example.tests.utils;

import com.example.petstore.model.Order;

import java.util.Optional;

public class OrderExpectedResult {
    public long id;
    public long petId;
    public int quantity;
    public Order.StatusEnum status;
    public boolean complete;
    public OrderExpectedResult(long id, long petId, int quantity, Order.StatusEnum status, boolean complete) {
        this.id = id;
        this.petId = petId;
        this.quantity = quantity;
        this.status = status;
        this.complete = complete;
    }
    public static OrderExpectedResult from(Order order) {
        return Optional.ofNullable(order)
                .map(o -> new OrderExpectedResult(
                        o.getId(),
                        o.getPetId(),
                        o.getQuantity(),
                        o.getStatus(),
                        o.getComplete()
                ))
                .orElseThrow(() -> new NullPointerException("Order cannot be null"));
    }
}
