package com.brianchiu.jwtdemo.entity;

import java.util.Date;

public class Product {

    private int id;
    private String name;
    private double unitPrice;
    private int stock;
    private String description;
    private String photoUrl;
    private String brand;
    private int discount;
    private String type;
    private Date shelfDate;

    public Product() {
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getShelfDate() {
        return shelfDate;
    }

    public void setShelfDate(Date shelfDate) {
        this.shelfDate = shelfDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unitPrice=" + unitPrice +
                ", stock=" + stock +
                ", description='" + description + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", brand='" + brand + '\'' +
                ", discount=" + discount +
                ", type='" + type + '\'' +
                ", shelfDate=" + shelfDate +
                '}';
    }
}
