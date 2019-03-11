package com.example.publicmart;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class RealmShopModel extends RealmObject {

    private @PrimaryKey
    String ProductKey ;
   // String ProductCode,BrandName,ShortDesc,ProductDetails;

    public RealmShopModel() {

    }

    public RealmShopModel(String productKey /* String productCode, String brandName, String shortDesc, String productDetails*/) {
        ProductKey = productKey;
//        ProductCode = productCode;
//        BrandName = brandName;
//        ShortDesc = shortDesc;
//        ProductDetails = productDetails;
    }


    public String getProductKey() {
        return ProductKey;
    }

    public void setProductKey(String productKey) {
        ProductKey = productKey;
    }

//    public String getProductCode() {
//        return ProductCode;
//    }
//
//    public void setProductCode(String productCode) {
//        ProductCode = productCode;
//    }
//
//    public String getBrandName() {
//        return BrandName;
//    }
//
//    public void setBrandName(String brandName) {
//        BrandName = brandName;
//    }
//
//    public String getShortDesc() {
//        return ShortDesc;
//    }
//
//    public void setShortDesc(String shortDesc) {
//        ShortDesc = shortDesc;
//    }
//
//    public String getProductDetails() {
//        return ProductDetails;
//    }
//
//    public void setProductDetails(String productDetails) {
//        ProductDetails = productDetails;
//    }

}
