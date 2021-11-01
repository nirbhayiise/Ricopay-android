
package com.np.ricopayapp.responsemodels.search_account_respo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class SearchAccountWrapper {

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
