package com.publicmart.android.RetrofitUtils.RetrofitResponseClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.publicmart.android.Utils.CONSTANTS;

import java.util.List;

public class GetProductDetailsResponse {

    @SerializedName("responseCode")
    @Expose
    private String code;

    @SerializedName("responseMessage")
    @Expose
    private String message;

    @SerializedName("result")
    @Expose
    private ResultArray result;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ResultArray getResult() {
        return result;
    }

    public class ResultArray {

        @SerializedName("Table")
        @Expose
        private List<Table>table;

        @SerializedName("Table1")
        @Expose
        private List<Table1>table1;

        public List<Table> getTable() {
            return table;
        }

        public List<Table1> getTable1() {
            return table1;
        }
    }

    public class Table {

        @SerializedName(CONSTANTS.ProductKey)
        @Expose
        private String ProductKey;

        @SerializedName(CONSTANTS.ProductCode)
        @Expose
        private String ProductCode;

        @SerializedName(CONSTANTS.BrandName)
        @Expose
        private String BrandName;

        @SerializedName(CONSTANTS.ShortDesc)
        @Expose
        private String ShortDesc;

        @SerializedName(CONSTANTS.MRP)
        @Expose
        private String MRP;

        @SerializedName(CONSTANTS.BV)
        @Expose
        private String BV;

        @SerializedName(CONSTANTS.ProductDetails)
        @Expose
        private String ProductDetails;

        @SerializedName(CONSTANTS.Material)
        @Expose
        private String Material;

        @SerializedName(CONSTANTS.Size)
        @Expose
        private String Size;

        @SerializedName(CONSTANTS.Color)
        @Expose
        private String Color;


        public String getProductKey() {
            return ProductKey;
        }

        public String getProductCode() {
            return ProductCode;
        }

        public String getBrandName() {
            return BrandName;
        }

        public String getShortDesc() {
            return ShortDesc;
        }

        public String getMRP() {
            return MRP;
        }

        public String getBV() {
            return BV;
        }

        public String getProductDetails() {
            return ProductDetails;
        }

        public String getMaterial() {
            return Material;
        }

        public String getSize() {
            return Size;
        }

        public String getColor() {
            return Color;
        }
    }

    public class Table1 {


        @SerializedName(CONSTANTS.ProductImageID)
        @Expose
        private String ProductImageID;

        @SerializedName(CONSTANTS.ImagePath)
        @Expose
        private String ImagePath;

        public String getProductImageID() {
            return ProductImageID;
        }

        public String getImagePath() {
            return ImagePath;
        }
    }

}
