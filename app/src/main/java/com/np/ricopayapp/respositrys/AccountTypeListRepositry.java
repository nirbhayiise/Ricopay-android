package com.np.ricopayapp.respositrys;


import android.app.ProgressDialog;
import android.content.Context;

import com.np.ricopayapp.interfaces.AccountTypeList_CallBack;
import com.np.ricopayapp.interfaces.StaffList_CallBack;
import com.np.ricopayapp.responsemodels.account_type_response_data.AccountTypeWrapper;
import com.np.ricopayapp.responsemodels.staff_list_response.StaffListWrapper;
import com.np.ricopayapp.services.APIs;
import com.np.ricopayapp.services.SingletonObjectAccess;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountTypeListRepositry {
    public static AccountTypeListRepositry instaceObject=null;
    public static AccountTypeListRepositry getInstance(){
        if (instaceObject==null)
        {
            instaceObject=new AccountTypeListRepositry();
        }
        return instaceObject;
    }

    private AccountTypeListRepositry() {

    }

    public void getAcounttype(Context cnt, AccountTypeList_CallBack lgacallback)
    {
        ProgressDialog pd=new ProgressDialog(cnt);
        pd.setMessage("Please wait...");
        pd.show();
        APIs apiService = SingletonObjectAccess.getApiService();
        Call<AccountTypeWrapper> call = apiService.getAcounttypeLs();
        call.enqueue(new Callback<AccountTypeWrapper>() {
            @Override
            public void onResponse(Call<AccountTypeWrapper> call, Response<AccountTypeWrapper> response) {

                pd.dismiss();
                if (response.body().getResponse().getStatus().equals("1"))
                {
                    lgacallback.onAccoutTypeList(response.body().getResponse().getData());
                }
            }

            @Override
            public void onFailure(Call<AccountTypeWrapper> call, Throwable t) {

            }
        });
    }



}
