package com.np.ricopayapp.respositrys;

import android.app.ProgressDialog;
import android.content.Context;

import com.np.ricopayapp.interfaces.StaffLoginCallBack;
import com.np.ricopayapp.responsemodels.staff_login.StaffLoginWrapper;
import com.np.ricopayapp.services.APIs;
import com.np.ricopayapp.services.SingletonObjectAccess;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StaffLoginRepositry {
    public static StaffLoginRepositry _object=null;
    public static StaffLoginRepositry getInstance(){
        if (_object==null)
        {
            _object=new StaffLoginRepositry();
        }
        return _object;
    }

    private StaffLoginRepositry() {


    }
    public void LoginInvok(String username, String pass, Context cnt, StaffLoginCallBack r_callback){
        ProgressDialog pd=new ProgressDialog(cnt);
        pd.setMessage("Please wait...");
        pd.show();
        APIs apiService = SingletonObjectAccess.getApiService();
        Call<StaffLoginWrapper> call = apiService.loginStaff( username,  pass);
        call.enqueue(new Callback<StaffLoginWrapper>() {
            @Override
            public void onResponse(Call<StaffLoginWrapper> call, Response<StaffLoginWrapper> response) {
                pd.dismiss();
                String flg=response.body().getResponse().getStatus();
                if(flg.equals("1"))
                {
                    r_callback.onLoginSuccessful(response.body().getResponse().getData());
                }else{
                    r_callback.onLoginFailed(response.body().getResponse().getMessage());
                }
            }

            @Override
            public void onFailure(Call<StaffLoginWrapper> call, Throwable t) {
                pd.dismiss();
                r_callback.onLoginFailed("Something went wrong");
            }
        });
    }



}
