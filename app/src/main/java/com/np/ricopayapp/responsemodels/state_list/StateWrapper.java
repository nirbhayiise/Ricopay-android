
package com.np.ricopayapp.responsemodels.state_list;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class StateWrapper {

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
