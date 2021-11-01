package com.np.ricopayapp.respositrys;


import android.app.ProgressDialog;
import android.content.Context;

import com.np.ricopayapp.interfaces.LGASpinnerData_CallBack;
import com.np.ricopayapp.responsemodels.lga_list.LGAWrapper;
import com.np.ricopayapp.responsemodels.staff_login.StaffLoginWrapper;
import com.np.ricopayapp.services.APIs;
import com.np.ricopayapp.services.SingletonObjectAccess;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LGAListingRepositry {
    public static LGAListingRepositry instaceObject=null;
    public static LGAListingRepositry getInstance(){
        if (instaceObject==null)
        {
            instaceObject=new LGAListingRepositry();
        }
        return instaceObject;
    }

    private LGAListingRepositry() {

    }

    public void getLGAData(String satteId,Context cnt, LGASpinnerData_CallBack lgacallback)
    {
        ProgressDialog pd=new ProgressDialog(cnt);
        pd.setMessage("Please wait...");
        pd.show();
        APIs apiService = SingletonObjectAccess.getApiService();
        Call<LGAWrapper> call = apiService.getLGA(satteId);
        call.enqueue(new Callback<LGAWrapper>() {
            @Override
            public void onResponse(Call<LGAWrapper> call, Response<LGAWrapper> response) {

                pd.dismiss();
                if (response.body().getResponse().getStatus().equals("1"))
                {
                    lgacallback.onLGAList(response.body().getResponse().getData());
                }
            }

            @Override
            public void onFailure(Call<LGAWrapper> call, Throwable t) {

            }
        });
    }



}
