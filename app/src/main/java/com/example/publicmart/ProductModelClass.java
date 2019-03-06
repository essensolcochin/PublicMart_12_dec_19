package com.example.publicmart;

public class ProductModelClass {

    String ProductKey,ProductName,ImagePath,Description,MRP,BV;


    public ProductModelClass(String productKey, String productName, String imagePath, String description, String MRP, String BV) {
        ProductKey = productKey;
        ProductName = productName;
        ImagePath = imagePath;
        Description = description;
        this.MRP = MRP;
        this.BV = BV;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getMRP() {
        return MRP;
    }

    public void setMRP(String MRP) {
        this.MRP = MRP;
    }

    public String getBV() {
        return BV;
    }

    public void setBV(String BV) {
        this.BV = BV;
    }

    public String getProductKey() {
        return ProductKey;
    }

    public void setProductKey(String productKey) {
        ProductKey = productKey;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }
}
