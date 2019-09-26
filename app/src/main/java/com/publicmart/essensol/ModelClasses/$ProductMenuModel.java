package com.publicmart.essensol.ModelClasses;

public class $ProductMenuModel {

   private String CategoryKey,CategoryName,ImagePath;
   private  boolean isHeader;

    public $ProductMenuModel(String categoryKey, String categoryName, String imagePath,boolean isHeader) {
        CategoryKey = categoryKey;
        CategoryName = categoryName;
        ImagePath = imagePath;
        this.isHeader = isHeader;

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

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }
}
