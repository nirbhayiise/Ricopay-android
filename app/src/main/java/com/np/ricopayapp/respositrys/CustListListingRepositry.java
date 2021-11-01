package com.np.ricopayapp.respositrys;

import android.app.ProgressDialog;
import android.content.Context;

import com.np.ricopayapp.interfaces.CustListCallback;
import com.np.ricopayapp.interfaces.StoreListCallback;
import com.np.ricopayapp.responsemodels.cut_regist_list_resp.CustListWrapper;
import com.np.ricopayapp.responsemodels.store_response.StoreWrapper;
import com.np.ricopayapp.services.APIs;
import com.np.ricopayapp.services.SingletonObjectAccess;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CustListListingRepositry {
    public static CustListListingRepositry instaceObject=null;
    public static CustListListingRepositry getInstance(){
        if (instaceObject==null)
        {
            instaceObject=new CustListListingRepositry();
        }
        return instaceObject;
    }

    private CustListListingRepositry() {

    }

    public void getCusLsList(String sid,Context cnt, CustListCallback lgacallback)
    {
        ProgressDialog pd=new ProgressDialog(cnt);
        pd.setMessage("Please wait...");
        pd.show();
        APIs apiService = SingletonObjectAccess.getApiService();
        Call<CustListWrapper> call = apiService.getCustList(sid);
        call.enqueue(new Callback<CustListWrapper>() {
            @Override
            public void onResponse(Call<CustListWrapper> call, Response<CustListWrapper> response) {
                pd.dismiss();
                if (response.body().getResponse().getStatus().equals("1"))
                {
                    lgacallback.onCustListSuccess(response.body().getResponse().getData());
                }else {
                    lgacallback.onCustListFailed(response.body().getResponse().getMessage());
                }
            }

            @Override
            public void onFailure(Call<CustListWrapper> call, Throwable t) {

            }
        });
    }


}
