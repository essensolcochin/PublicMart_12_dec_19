package com.publicmart.essensol.ModelClasses;

public class SearchFilterModel {

private  String ProductKey,CategoryKey,CategoryName,BrandName,ShortDesc;

    public SearchFilterModel(String productKey, String categoryKey, String categoryName, String brandName, String shortDesc) {
        ProductKey = productKey;
        CategoryKey = categoryKey;
        CategoryName = categoryName;
        BrandName = brandName;
        ShortDesc = shortDesc;
    }

    public String getProductKey() {
        return ProductKey;
    }

    public void setProductKey(String productKey) {
        ProductKey = productKey;
    }

    public String getCategoryKey() {
        return CategoryKey;
    }

    public void setCategoryKey(String categoryKey) {
        CategoryKey = categoryKey;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
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

    @Override
    public String toString() {
        return CategoryKey;
    }
}
