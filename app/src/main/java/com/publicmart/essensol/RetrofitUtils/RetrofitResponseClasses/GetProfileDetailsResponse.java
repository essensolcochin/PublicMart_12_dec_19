package com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetProfileDetailsResponse {

    @SerializedName("responseCode")
    @Expose
    private String code;

    @SerializedName("responseMessage")
    @Expose
    private String message;

    @SerializedName("result")
    @Expose
    private List<ResultArray> response;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResultArray> getResponse() {
        return response;
    }

    public void setResponse(List<ResultArray> response) {
        this.response = response;
    }

    public  class ResultArray{

        @SerializedName("CustCode")
        @Expose
        private String CustCode;

        @SerializedName("CustomerName")
        @Expose
        private String CustomerName;

        @SerializedName("MobileNo")
        @Expose
        private String MobileNo;

        @SerializedName("Email")
        @Expose
        private String Email;

        @SerializedName("DOB")
        @Expose
        private String DOB;


        @SerializedName("Address")
        @Expose
        private String Address;

        @SerializedName("AcntHolderName")
        @Expose
        private String AcntHolderName;

        @SerializedName("AcntNo")
        @Expose
        private String AcntNo;


        @SerializedName("BankName")
        @Expose
        private String BankName;

        @SerializedName("BranchName")
        @Expose
        private String BranchName;

        @SerializedName("IFSCCode")
        @Expose
        private String IFSCCode;

        public String getCustCode() {
            return CustCode;
        }

        public void setCustCode(String custCode) {
            CustCode = custCode;
        }

        public String getCustomerName() {
            return CustomerName;
        }

        public void setCustomerName(String customerName) {
            CustomerName = customerName;
        }

        public String getMobileNo() {
            return MobileNo;
        }

        public void setMobileNo(String mobileNo) {
            MobileNo = mobileNo;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String email) {
            Email = email;
        }

        public String getDOB() {
            return DOB;
        }

        public void setDOB(String DOB) {
            this.DOB = DOB;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String address) {
            Address = address;
        }

        public String getAcntHolderName() {
            return AcntHolderName;
        }

        public void setAcntHolderName(String acntHolderName) {
            AcntHolderName = acntHolderName;
        }

        public String getAcntNo() {
            return AcntNo;
        }

        public void setAcntNo(String acntNo) {
            AcntNo = acntNo;
        }

        public String getBankName() {
            return BankName;
        }

        public void setBankName(String bankName) {
            BankName = bankName;
        }

        public String getBranchName() {
            return BranchName;
        }

        public void setBranchName(String branchName) {
            BranchName = branchName;
        }

        public String getIFSCCode() {
            return IFSCCode;
        }

        public void setIFSCCode(String IFSCCode) {
            this.IFSCCode = IFSCCode;
        }
    }


}
