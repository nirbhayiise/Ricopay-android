
package com.np.ricopayapp.responsemodels.login_user;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Data {

    @SerializedName("auth_id")
    @Expose
    private String authId;
    @SerializedName("auth_names")
    @Expose
    private String authNames;
    @SerializedName("auth_mobile")
    @Expose
    private String authMobile;
    @SerializedName("auth_username")
    @Expose
    private String authUsername;
    @SerializedName("auth_password")
    @Expose
    private String authPassword;
    @SerializedName("auth_created")
    @Expose
    private String authCreated;
    @SerializedName("auth_status")
    @Expose
    private String authStatus;

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getAuthNames() {
        return authNames;
    }

    public void setAuthNames(String authNames) {
        this.authNames = authNames;
    }

    public String getAuthMobile() {
        return authMobile;
    }

    public void setAuthMobile(String authMobile) {
        this.authMobile = authMobile;
    }

    public String getAuthUsername() {
        return authUsername;
    }

    public void setAuthUsername(String authUsername) {
        this.authUsername = authUsername;
    }

    public String getAuthPassword() {
        return authPassword;
    }

    public void setAuthPassword(String authPassword) {
        this.authPassword = authPassword;
    }

    public String getAuthCreated() {
        return authCreated;
    }

    public void setAuthCreated(String authCreated) {
        this.authCreated = authCreated;
    }

    public String getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(String authStatus) {
        this.authStatus = authStatus;
    }

}
