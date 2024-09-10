package com.rs.product.cart;

public class CartRequest {

    private int userId;
    private int id_stock;
    private int quantity;

    public CartRequest(int userId, int id_stock, int quantity) {
        this.userId = userId;
        this.id_stock = id_stock;
        this.quantity = quantity;
    }

    // Getters and Setters


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId_stock() {
        return id_stock;
    }

    public void setId_stock(int id_stock) {
        this.id_stock = id_stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

