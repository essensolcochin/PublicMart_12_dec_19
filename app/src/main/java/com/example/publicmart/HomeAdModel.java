package com.example.publicmart;

public class HomeAdModel {
    String AdvImageKey,ImageDesc,ImagePath;

    public HomeAdModel(String advImageKey, String imageDesc, String imagePath) {
        AdvImageKey = advImageKey;
        ImageDesc = imageDesc;
        ImagePath = imagePath;
    }

    public String getAdvImageKey() {
        return AdvImageKey;
    }

    public void setAdvImageKey(String advImageKey) {
        AdvImageKey = advImageKey;
    }

    public String getImageDesc() {
        return ImageDesc;
    }

    public void setImageDesc(String imageDesc) {
        ImageDesc = imageDesc;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }
}
