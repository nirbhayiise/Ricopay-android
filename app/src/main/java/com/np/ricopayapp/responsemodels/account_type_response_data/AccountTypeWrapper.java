
package com.np.ricopayapp.responsemodels.account_type_response_data;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AccountTypeWrapper {

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
