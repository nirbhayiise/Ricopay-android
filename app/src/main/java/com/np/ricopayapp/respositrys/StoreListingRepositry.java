package com.np.ricopayapp.respositrys;

import android.app.ProgressDialog;
import android.content.Context;

import com.np.ricopayapp.interfaces.LGASpinnerData_CallBack;
import com.np.ricopayapp.interfaces.SignupCallBack;
import com.np.ricopayapp.interfaces.StoreListCallback;
import com.np.ricopayapp.responsemodels.authresponse.AuthWrapper;
import com.np.ricopayapp.responsemodels.lga_list.LGAWrapper;
import com.np.ricopayapp.responsemodels.store_response.StoreWrapper;
import com.np.ricopayapp.services.APIs;
import com.np.ricopayapp.services.SingletonObjectAccess;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StoreListingRepositry {
    public static StoreListingRepositry instaceObject=null;
    public static StoreListingRepositry getInstance(){
        if (instaceObject==null)
        {
            instaceObject=new StoreListingRepositry();
        }
        return instaceObject;
    }

    private StoreListingRepositry() {

    }

    public void getstoreList(Context cnt, StoreListCallback lgacallback)
    {
        ProgressDialog pd=new ProgressDialog(cnt);
        pd.setMessage("Please wait...");
        pd.show();
        APIs apiService = SingletonObjectAccess.getApiService();
        Call<StoreWrapper> call = apiService.getStore();
        call.enqueue(new Callback<StoreWrapper>() {
            @Override
            public void onResponse(Call<StoreWrapper> call, Response<StoreWrapper> response) {
                pd.dismiss();
                if (response.body().getResponse().getStatus().equals("1"))
                {
                    lgacallback.onStoreSuccess(response.body().getResponse().getData());
                }
            }

            @Override
            public void onFailure(Call<StoreWrapper> call, Throwable t) {

            }
        });
    }


}
