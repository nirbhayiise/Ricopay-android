
package com.np.ricopayapp.responsemodels.info_reponse;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class InfoWrapper {

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
