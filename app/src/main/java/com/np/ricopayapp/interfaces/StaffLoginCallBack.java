package com.np.ricopayapp.interfaces;

import com.np.ricopayapp.responsemodels.staff_login.Data;

public interface StaffLoginCallBack {
    void onLoginSuccessful(Data data);
    void onLoginFailed(String msg);
}
