package com.np.ricopayapp.respositrys;

import android.app.ProgressDialog;
import android.content.Context;

import com.np.ricopayapp.interfaces.LoginCallBack;
import com.np.ricopayapp.interfaces.RegisterUserCallBack;
import com.np.ricopayapp.responsemodels.authresponse.AuthWrapper;
import com.np.ricopayapp.responsemodels.login_user.LoginUserWrapper;
import com.np.ricopayapp.responsemodels.register_response.RegisterWrapper;
import com.np.ricopayapp.services.APIs;
import com.np.ricopayapp.services.SingletonObjectAccess;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginRepositry {
    public static LoginRepositry _object=null;
    public static LoginRepositry getInstance(){
        if (_object==null)
        {
            _object=new LoginRepositry();
        }
        return _object;
    }

    private LoginRepositry() {


    }
    public void LoginInvok(String username, String pass, Context cnt, LoginCallBack r_callback){
       ProgressDialog pd=new ProgressDialog(cnt);
        pd.setMessage("Please wait...");
        pd.show();
        APIs apiService = SingletonObjectAccess.getApiService();
        Call<LoginUserWrapper> call = apiService.loginuser( username,  pass);
        call.enqueue(new Callback<LoginUserWrapper>() {
            @Override
            public void onResponse(Call<LoginUserWrapper> call, Response<LoginUserWrapper> response) {
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
            public void onFailure(Call<LoginUserWrapper> call, Throwable t) {
                pd.dismiss();
                r_callback.onLoginFailed("Something went wrong");
            }
        });
    }



}
