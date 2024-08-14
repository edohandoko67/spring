package com.rs.product;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class ProductInfo {
    @NotNull
    @Length(min = 5, max = 16)
    private String name;

    private float price;

    @Length(min = 5, max = 50)
    private String pembuat;

    public void ProductInfo() {}

    public ProductInfo(String name, float price, String pembuat) {
        this.name = name;
        this.price = price;
        this.pembuat = pembuat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPembuat() {
        return pembuat;
    }

    public void setPembuat(String pembuat) {
        this.pembuat = pembuat;
    }
}
