
package com.np.ricopayapp.responsemodels.cut_regist_list_resp;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CustListWrapper {

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
