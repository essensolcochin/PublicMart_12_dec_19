package com.example.publicmart;

public class $ProductMenuModel {

    String CategoryKey,CategoryName,ImagePath;

    public $ProductMenuModel(String categoryKey, String categoryName, String imagePath) {
        CategoryKey = categoryKey;
        CategoryName = categoryName;
        ImagePath = imagePath;

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
}
