package org.example.tests.utils;

import com.example.petstore.model.Order;

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
        return new OrderExpectedResult(
                order.getId(),
                order.getPetId(),
                order.getQuantity(),
                order.getStatus(),
                order.getComplete()
        );
    }
}
