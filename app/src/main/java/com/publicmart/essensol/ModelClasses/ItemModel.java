package com.publicmart.essensol.ModelClasses;

public class ItemModel {

    private String name,price;
    private  boolean isHeader;

    public ItemModel(String name, String price, boolean isHeader) {
        this.name = name;
        this.price = price;
        this.isHeader = isHeader;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }
}
