package com.publicmart.android;

public class HomeAdModel {
    String AdvImageKey,ImagePath;

    public HomeAdModel(String advImageKey, String imagePath) {
        AdvImageKey = advImageKey;

        ImagePath = imagePath;
    }

    public String getAdvImageKey() {
        return AdvImageKey;
    }

    public void setAdvImageKey(String advImageKey) {
        AdvImageKey = advImageKey;
    }



    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }
}
