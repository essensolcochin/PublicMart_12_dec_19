package com.example.publicmart;

public class ProductModelClass {

    String ProductKey,ProductName,ImagePath;

    public ProductModelClass(String productKey, String productName, String imagePath) {
        ProductKey = productKey;
        ProductName = productName;
        ImagePath = imagePath;
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
