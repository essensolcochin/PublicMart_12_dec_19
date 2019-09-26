package com.publicmart.essensol.ModelClasses;

public class OrderStatusModel {


 String ProductKey,BrandName,ShortDesc,Amount,BV,ImagePath,OrderStatusKey,OrderStatusName;

    public OrderStatusModel(String productKey, String brandName, String shortDesc, String amount, String BV, String imagePath, String orderStatusKey, String orderStatusName) {
        ProductKey = productKey;
        BrandName = brandName;
        ShortDesc = shortDesc;
        Amount = amount;
        this.BV = BV;
        ImagePath = imagePath;
        OrderStatusKey = orderStatusKey;
        OrderStatusName = orderStatusName;
    }

    public String getProductKey() {
        return ProductKey;
    }

    public void setProductKey(String productKey) {
        ProductKey = productKey;
    }

    public String getBrandName() {
        return BrandName;
    }

    public String getBV() {
        return BV;
    }

    public void setBV(String BV) {
        this.BV = BV;
    }

    public void setBrandName(String brandName) {
        BrandName = brandName;
    }

    public String getShortDesc() {
        return ShortDesc;
    }

    public void setShortDesc(String shortDesc) {
        ShortDesc = shortDesc;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public String getOrderStatusKey() {
        return OrderStatusKey;
    }

    public void setOrderStatusKey(String orderStatusKey) {
        OrderStatusKey = orderStatusKey;
    }

    public String getOrderStatusName() {
        return OrderStatusName;
    }

    public void setOrderStatusName(String orderStatusName) {
        OrderStatusName = orderStatusName;
    }
}
