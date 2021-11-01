
package com.np.ricopayapp.responsemodels.login_user;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LoginUserWrapper {

    @SerializedName("response")
    @Expose
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

}
