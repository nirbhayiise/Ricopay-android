package com.np.ricopayapp.respositrys;


import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.np.ricopayapp.interfaces.LGASpinnerData_CallBack;
import com.np.ricopayapp.interfaces.SpinnerData_CallBack;
import com.np.ricopayapp.responsemodels.lga_list.LGAWrapper;
import com.np.ricopayapp.responsemodels.state_list.StateWrapper;
import com.np.ricopayapp.services.APIs;
import com.np.ricopayapp.services.SingletonObjectAccess;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StateListingRepositry {
    public static StateListingRepositry instaceObject=null;
    public static StateListingRepositry getInstance(){
        if (instaceObject==null)
        {
            instaceObject=new StateListingRepositry();
        }
        return instaceObject;
    }

    private StateListingRepositry() {

    }
    public void getState(Context cnt, SpinnerData_CallBack lgacallback)
    {
        ProgressDialog pd=new ProgressDialog(cnt);
        pd.setMessage("Please wait...");
        pd.show();
        APIs apiService = SingletonObjectAccess.getApiService();
        Call<StateWrapper> call = apiService.getState();
        call.enqueue(new Callback<StateWrapper>() {
            @Override
            public void onResponse(Call<StateWrapper> call, Response<StateWrapper> response) {

                if(response.body().getResponse().getStatus().equals("1")){
                    lgacallback.onStateList(response.body().getResponse().getData());
                }else{
                    Toast.makeText(cnt, ""+response.body().getResponse().getMessage(), Toast.LENGTH_SHORT).show();
                }
                pd.dismiss();
            }

            @Override
            public void onFailure(Call<StateWrapper> call, Throwable t) {
                pd.dismiss();
            }
        });
    }



}
