package com.np.ricopayapp.interfaces;

import com.np.ricopayapp.responsemodels.login_user.Data;

public interface LoginCallBack {
    void onLoginSuccessful(Data data);
    void onLoginFailed(String msg);
}
