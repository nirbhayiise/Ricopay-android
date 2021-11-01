package com.np.ricopayapp.respositrys;

import android.app.ProgressDialog;
import android.content.Context;

import com.np.ricopayapp.interfaces.CustomerRegisterCallback;
import com.np.ricopayapp.interfaces.RegisterUserCallBack;
import com.np.ricopayapp.responsemodels.info_reponse.InfoWrapper;
import com.np.ricopayapp.responsemodels.register_response.RegisterWrapper;
import com.np.ricopayapp.services.APIs;
import com.np.ricopayapp.services.SingletonObjectAccess;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EasyPayApplicationRepositry {
    public static EasyPayApplicationRepositry signupObject=null;
    public static EasyPayApplicationRepositry getInstance(){
        if (signupObject==null)
        {
            signupObject=new EasyPayApplicationRepositry();
        }
        return signupObject;
    }

    private EasyPayApplicationRepositry() {


    }
    public void reqEasyPay(String acc_no,String staff_id, String product, String acc_type, String payment_mode, String amount, String reg_fee, Context cnt, CustomerRegisterCallback r_callback){
       ProgressDialog pd=new ProgressDialog(cnt);
        pd.setMessage("Please wait...");
        pd.show();
        APIs apiService = SingletonObjectAccess.getApiService();
        Call<InfoWrapper> call = apiService.easypayReq(acc_no,staff_id, product, acc_type, payment_mode, amount, reg_fee);
        call.enqueue(new Callback<InfoWrapper>() {
            @Override
            public void onResponse(Call<InfoWrapper> call, Response<InfoWrapper> response) {
                pd.dismiss();
                if(response.body().getResponse().getStatus().equals("1"))
                {
                    r_callback.onRegCustomerRegisterSuccessful(response.body().getResponse().getMessage());
                }else{
                    r_callback.onCustomerRegisterUserFailed(response.body().getResponse().getMessage());
                }
            }

            @Override
            public void onFailure(Call<InfoWrapper> call, Throwable t) {
                pd.dismiss();
                r_callback.onCustomerRegisterUserFailed(t.toString());
            }
        });
    }



}
