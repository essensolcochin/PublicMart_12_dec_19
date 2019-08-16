package com.publicmart.android.ModelClasses;

public class ProductModelClass {

    String ProductKey,BrandName,ShortDesc,MRP,BV,ImagePath;


    public ProductModelClass(String productKey, String brandName, String shortDesc, String MRP, String BV, String imagePath) {
        ProductKey = productKey;
        BrandName = brandName;
        ShortDesc = shortDesc;
        this.MRP = MRP;
        this.BV = BV;
        ImagePath = imagePath;
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

    public void setBrandName(String brandName) {
        BrandName = brandName;
    }

    public String getShortDesc() {
        return ShortDesc;
    }

    public void setShortDesc(String shortDesc) {
        ShortDesc = shortDesc;
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

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }


}
