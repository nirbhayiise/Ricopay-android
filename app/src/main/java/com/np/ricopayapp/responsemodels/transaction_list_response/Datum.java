
package com.np.ricopayapp.responsemodels.transaction_list_response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Datum {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("acc_no")
    @Expose
    private String accNo;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("transaction")
    @Expose
    private String transaction;
    @SerializedName("easypay_type")
    @Expose
    private String easypayType;
    @SerializedName("staff_id")
    @Expose
    private String staffId;
    @SerializedName("date_updated")
    @Expose
    private String dateUpdated;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("auth_id")
    @Expose
    private String authId;
    @SerializedName("names")
    @Expose
    private String names;
    @SerializedName("marital_status")
    @Expose
    private String maritalStatus;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("lga")
    @Expose
    private String lga;
    @SerializedName("c_address")
    @Expose
    private String cAddress;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("reg_state")
    @Expose
    private String regState;
    @SerializedName("c_state")
    @Expose
    private String cState;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("acc_type")
    @Expose
    private String accType;
    @SerializedName("pay_mode")
    @Expose
    private String payMode;
    @SerializedName("reg_fee")
    @Expose
    private String regFee;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("acc_officer_id")
    @Expose
    private String accOfficerId;
    @SerializedName("branch_id")
    @Expose
    private String branchId;
    @SerializedName("reg_date")
    @Expose
    private String regDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public String getEasypayType() {
        return easypayType;
    }

    public void setEasypayType(String easypayType) {
        this.easypayType = easypayType;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getLga() {
        return lga;
    }

    public void setLga(String lga) {
        this.lga = lga;
    }

    public String getcAddress() {
        return cAddress;
    }

    public void setcAddress(String cAddress) {
        this.cAddress = cAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegState() {
        return regState;
    }

    public void setRegState(String regState) {
        this.regState = regState;
    }

    public String getcState() {
        return cState;
    }

    public void setcState(String cState) {
        this.cState = cState;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }

    public String getRegFee() {
        return regFee;
    }

    public void setRegFee(String regFee) {
        this.regFee = regFee;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAccOfficerId() {
        return accOfficerId;
    }

    public void setAccOfficerId(String accOfficerId) {
        this.accOfficerId = accOfficerId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

}
