package com.np.ricopayapp.respositrys;

import android.app.ProgressDialog;
import android.content.Context;


import com.np.ricopayapp.interfaces.RegisterUserCallBack;
import com.np.ricopayapp.interfaces.SignupCallBack;
import com.np.ricopayapp.interfaces.StoreListCallback;
import com.np.ricopayapp.responsemodels.authresponse.AuthWrapper;
import com.np.ricopayapp.responsemodels.register_response.RegisterWrapper;
import com.np.ricopayapp.responsemodels.store_response.StoreWrapper;
import com.np.ricopayapp.services.APIs;
import com.np.ricopayapp.services.SingletonObjectAccess;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignupRepositry {
    public static SignupRepositry signupObject=null;
    public static SignupRepositry getInstance(){
        if (signupObject==null)
        {
            signupObject=new SignupRepositry();
        }
        return signupObject;
    }

    private SignupRepositry() {


    }
    public void registerInvok(String auth_names, String auth_mobile, String auth_username, String auth_password, Context cnt, RegisterUserCallBack r_callback){
       ProgressDialog pd=new ProgressDialog(cnt);
        pd.setMessage("Please wait...");
        pd.show();
        APIs apiService = SingletonObjectAccess.getApiService();
        Call<RegisterWrapper> call = apiService.registerUser( auth_names,  auth_mobile,  auth_username, auth_password);
        call.enqueue(new Callback<RegisterWrapper>() {
            @Override
            public void onResponse(Call<RegisterWrapper> call, Response<RegisterWrapper> response) {
                pd.dismiss();
                if(response.body().getResponse().getStatus().equals("1"))
                {
                    r_callback.onRegisterUserSuccessful(response.body().getResponse().getData());
                }else{
                    r_callback.onRegisterUserFailed(response.body().getResponse().getMessage());
                }
            }

            @Override
            public void onFailure(Call<RegisterWrapper> call, Throwable t) {
                pd.dismiss();
                r_callback.onRegisterUserFailed(t.toString());
            }
        });
    }



}
