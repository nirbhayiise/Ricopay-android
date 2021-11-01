package com.np.ricopayapp.respositrys;


import android.app.ProgressDialog;
import android.content.Context;

import com.np.ricopayapp.interfaces.LGASpinnerData_CallBack;
import com.np.ricopayapp.interfaces.StaffList_CallBack;
import com.np.ricopayapp.responsemodels.lga_list.LGAWrapper;
import com.np.ricopayapp.responsemodels.staff_list_response.StaffListWrapper;
import com.np.ricopayapp.services.APIs;
import com.np.ricopayapp.services.SingletonObjectAccess;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StaffListRepositry {
    public static StaffListRepositry instaceObject=null;
    public static StaffListRepositry getInstance(){
        if (instaceObject==null)
        {
            instaceObject=new StaffListRepositry();
        }
        return instaceObject;
    }

    private StaffListRepositry() {

    }

    public void getstaffLs(Context cnt, StaffList_CallBack lgacallback)
    {
        ProgressDialog pd=new ProgressDialog(cnt);
        pd.setMessage("Please wait...");
        pd.show();
        APIs apiService = SingletonObjectAccess.getApiService();
        Call<StaffListWrapper> call = apiService.getstaffList();
        call.enqueue(new Callback<StaffListWrapper>() {
            @Override
            public void onResponse(Call<StaffListWrapper> call, Response<StaffListWrapper> response) {

                pd.dismiss();
                if (response.body().getResponse().getStatus().equals("1"))
                {
                    lgacallback.onLGAList(response.body().getResponse().getData());
                }
            }

            @Override
            public void onFailure(Call<StaffListWrapper> call, Throwable t) {

            }
        });
    }



}
