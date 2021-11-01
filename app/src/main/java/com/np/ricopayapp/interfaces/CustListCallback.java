package com.np.ricopayapp.interfaces;



import com.np.ricopayapp.responsemodels.cut_regist_list_resp.Datum;

import java.util.List;

public interface CustListCallback {
    void onCustListSuccess(List<Datum> data);
    void onCustListFailed(String msg);
}
