
package com.np.ricopayapp.responsemodels.transaction_list_response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class TransactionListWrapper {

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
