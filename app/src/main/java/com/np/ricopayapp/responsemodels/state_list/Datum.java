
package com.np.ricopayapp.responsemodels.state_list;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Datum {

    @SerializedName("sid")
    @Expose
    private String sid;
    @SerializedName("statename")
    @Expose
    private String statename;
    @SerializedName("ishost")
    @Expose
    private String ishost;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getStatename() {
        return statename;
    }

    public void setStatename(String statename) {
        this.statename = statename;
    }

    public String getIshost() {
        return ishost;
    }

    public void setIshost(String ishost) {
        this.ishost = ishost;
    }

}
