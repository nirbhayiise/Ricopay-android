
package com.np.ricopayapp.responsemodels.store_response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class StoreWrapper {

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
