
package com.np.ricopayapp.responsemodels.deposit_respo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DepositWrapper {

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
