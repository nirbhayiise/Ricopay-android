
package com.np.ricopayapp.responsemodels.staff_list_response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Datum {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("names")
    @Expose
    private String names;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("right_id")
    @Expose
    private String rightId;
    @SerializedName("store_id")
    @Expose
    private String storeId;
    @SerializedName("store")
    @Expose
    private String store;
    @SerializedName("users_status")
    @Expose
    private String usersStatus;
    @SerializedName("user_created")
    @Expose
    private String userCreated;
    @SerializedName("file_name")
    @Expose
    private String fileName;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("id_no")
    @Expose
    private String idNo;
    @SerializedName("relative1")
    @Expose
    private String relative1;
    @SerializedName("relative_mobile1")
    @Expose
    private String relativeMobile1;
    @SerializedName("relative2")
    @Expose
    private String relative2;
    @SerializedName("relative_mobile2")
    @Expose
    private String relativeMobile2;
    @SerializedName("guarantor")
    @Expose
    private String guarantor;
    @SerializedName("guarantor_mobile")
    @Expose
    private String guarantorMobile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRightId() {
        return rightId;
    }

    public void setRightId(String rightId) {
        this.rightId = rightId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getUsersStatus() {
        return usersStatus;
    }

    public void setUsersStatus(String usersStatus) {
        this.usersStatus = usersStatus;
    }

    public String getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(String userCreated) {
        this.userCreated = userCreated;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getRelative1() {
        return relative1;
    }

    public void setRelative1(String relative1) {
        this.relative1 = relative1;
    }

    public String getRelativeMobile1() {
        return relativeMobile1;
    }

    public void setRelativeMobile1(String relativeMobile1) {
        this.relativeMobile1 = relativeMobile1;
    }

    public String getRelative2() {
        return relative2;
    }

    public void setRelative2(String relative2) {
        this.relative2 = relative2;
    }

    public String getRelativeMobile2() {
        return relativeMobile2;
    }

    public void setRelativeMobile2(String relativeMobile2) {
        this.relativeMobile2 = relativeMobile2;
    }

    public String getGuarantor() {
        return guarantor;
    }

    public void setGuarantor(String guarantor) {
        this.guarantor = guarantor;
    }

    public String getGuarantorMobile() {
        return guarantorMobile;
    }

    public void setGuarantorMobile(String guarantorMobile) {
        this.guarantorMobile = guarantorMobile;
    }

}
