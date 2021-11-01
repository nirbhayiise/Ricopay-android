package com.np.ricopayapp.interfaces;

import com.np.ricopayapp.responsemodels.register_response.Data;

public interface RegisterUserCallBack {
    void onRegisterUserSuccessful(Data data);
    void onRegisterUserFailed(String msg);

}
