
package com.np.ricopayapp.responsemodels.lga_list;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Datum {

    @SerializedName("lid")
    @Expose
    private String lid;
    @SerializedName("sid")
    @Expose
    private String sid;
    @SerializedName("lganame")
    @Expose
    private String lganame;
    @SerializedName("ishost")
    @Expose
    private String ishost;

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getLganame() {
        return lganame;
    }

    public void setLganame(String lganame) {
        this.lganame = lganame;
    }

    public String getIshost() {
        return ishost;
    }

    public void setIshost(String ishost) {
        this.ishost = ishost;
    }

}
