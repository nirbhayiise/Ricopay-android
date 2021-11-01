package com.np.ricopayapp.respositrys;

import android.app.ProgressDialog;
import android.content.Context;

import com.np.ricopayapp.interfaces.DepositCallBack;
import com.np.ricopayapp.interfaces.RegisterUserCallBack;
import com.np.ricopayapp.interfaces.SearchAccountCallBack;
import com.np.ricopayapp.responsemodels.deposit_respo.DepositWrapper;
import com.np.ricopayapp.responsemodels.info_reponse.InfoWrapper;
import com.np.ricopayapp.responsemodels.register_response.RegisterWrapper;
import com.np.ricopayapp.responsemodels.search_account_respo.SearchAccountWrapper;
import com.np.ricopayapp.services.APIs;
import com.np.ricopayapp.services.SingletonObjectAccess;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DepositRepositry {
    public static DepositRepositry signupObject=null;
    public static DepositRepositry getInstance(){
        if (signupObject==null)
        {
            signupObject=new DepositRepositry();
        }
        return signupObject;
    }

    private DepositRepositry() {


    }
    public void invokDeposit(String acc_no, String amount, String transaction, String easypay_type, String staff_id, Context cnt, DepositCallBack r_callback){
       ProgressDialog pd=new ProgressDialog(cnt);
        pd.setMessage("Please wait...");
        pd.show();
        APIs apiService = SingletonObjectAccess.getApiService();
        Call<DepositWrapper> call = apiService.getDeposit( acc_no,  amount,  transaction,  easypay_type,  staff_id);
        call.enqueue(new Callback<DepositWrapper>() {
            @Override
            public void onResponse(Call<DepositWrapper> call, Response<DepositWrapper> response) {
                pd.dismiss();
                if(response.body().getResponse().getStatus().equals("1"))
                {
                    r_callback.onDepositSuccess(response.body().getResponse().getMessage());
                }else{
                    r_callback.onDepositFailed(response.body().getResponse().getMessage());
                }
            }

            @Override
            public void onFailure(Call<DepositWrapper> call, Throwable t) {
                pd.dismiss();
                r_callback.onDepositFailed(t.toString());
            }
        });
    }


    public void searchNameAccount(String acc_no, Context cnt, SearchAccountCallBack r_callback) {
        ProgressDialog pd = new ProgressDialog(cnt);
        pd.setMessage("Please wait...");
        pd.show();
        APIs apiService = SingletonObjectAccess.getApiService();
        Call<SearchAccountWrapper> call = apiService.findAccount(acc_no);
        call.enqueue(new Callback<SearchAccountWrapper>() {
            @Override
            public void onResponse(Call<SearchAccountWrapper> call, Response<SearchAccountWrapper> response) {
                pd.dismiss();
              if(response.body().getResponse().getStatus().equals("1"))
              {
                  r_callback.onFoundName(response.body().getResponse().getName());
              }else{
                  r_callback.NameNotFound(response.body().getResponse().getMessage());
              }

            }

            @Override
            public void onFailure(Call<SearchAccountWrapper> call, Throwable t) {
r_callback.NameNotFound(t.toString());
            }
        });
    }


    }
