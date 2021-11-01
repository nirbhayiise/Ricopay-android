package com.np.ricopayapp.respositrys;

import android.app.ProgressDialog;
import android.content.Context;

import com.np.ricopayapp.interfaces.StoreListCallback;
import com.np.ricopayapp.interfaces.UserListCallback;
import com.np.ricopayapp.responsemodels.store_response.StoreWrapper;
import com.np.ricopayapp.responsemodels.users_list.UsersWrapper;
import com.np.ricopayapp.services.APIs;
import com.np.ricopayapp.services.SingletonObjectAccess;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserListingRepositry {
    public static UserListingRepositry instaceObject=null;
    public static UserListingRepositry getInstance(){
        if (instaceObject==null)
        {
            instaceObject=new UserListingRepositry();
        }
        return instaceObject;
    }

    private UserListingRepositry() {

    }

    public void getUserList(Context cnt, UserListCallback lgacallback)
    {
        ProgressDialog pd=new ProgressDialog(cnt);
        pd.setMessage("Please wait...");
        pd.show();
        APIs apiService = SingletonObjectAccess.getApiService();
        Call<UsersWrapper> call = apiService.getUsers();
        call.enqueue(new Callback<UsersWrapper>() {
            @Override
            public void onResponse(Call<UsersWrapper> call, Response<UsersWrapper> response) {
                pd.dismiss();
                if (response.body().getResponse().getStatus().equals("1"))
                {
                    lgacallback.onUserListSuccess(response.body().getResponse().getData());
                }
            }

            @Override
            public void onFailure(Call<UsersWrapper> call, Throwable t) {

            }
        });
    }


}
