package com.publicmart.android.ModelClasses;

public class HomeAdModel {
   private String AdvImageKey,ImagePath,desc;

    public HomeAdModel(String advImageKey, String imagePath, String desc) {
        AdvImageKey = advImageKey;
        ImagePath = imagePath;
        this.desc = desc;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
