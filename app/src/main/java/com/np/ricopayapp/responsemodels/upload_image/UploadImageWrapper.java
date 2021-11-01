
package com.np.ricopayapp.responsemodels.upload_image;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UploadImageWrapper {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("filename")
    @Expose
    private String filename;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

}
