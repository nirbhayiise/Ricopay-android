package com.np.ricopayapp.respositrys;


import android.app.ProgressDialog;
import android.content.Context;

import com.np.ricopayapp.interfaces.StaffList_CallBack;
import com.np.ricopayapp.interfaces.TrascationListCallBack;
import com.np.ricopayapp.responsemodels.staff_list_response.StaffListWrapper;
import com.np.ricopayapp.responsemodels.transaction_list_response.TransactionListWrapper;
import com.np.ricopayapp.services.APIs;
import com.np.ricopayapp.services.SingletonObjectAccess;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrascationListRepositry {
    public static TrascationListRepositry instaceObject=null;
    public static TrascationListRepositry getInstance(){
        if (instaceObject==null)
        {
            instaceObject=new TrascationListRepositry();
        }
        return instaceObject;
    }

    private TrascationListRepositry() {

    }

    public void getranscationLs(String id, String dt, Context cnt, TrascationListCallBack lgacallback)
    {
        ProgressDialog pd=new ProgressDialog(cnt);
        pd.setMessage("Please wait...");
        pd.show();
        APIs apiService = SingletonObjectAccess.getApiService();
        Call<TransactionListWrapper> call = apiService.getTranscationLs(id,dt);
        call.enqueue(new Callback<TransactionListWrapper>() {
            @Override
            public void onResponse(Call<TransactionListWrapper> call, Response<TransactionListWrapper> response) {

                pd.dismiss();
                if (response.body().getResponse().getStatus().equals("1"))
                {
                    lgacallback.onTrascationList(response.body().getResponse().getData());
                }else{
                    lgacallback.noDataFound(response.body().getResponse().getMessage());
                }
            }

            @Override
            public void onFailure(Call<TransactionListWrapper> call, Throwable t) {
                lgacallback.noDataFound(t.toString());
            }
        });
    }



}
