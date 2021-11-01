package com.np.ricopayapp.interfaces;

import com.np.ricopayapp.responsemodels.authresponse.Data;

import java.util.List;

public interface SignupCallBack {
    void onSignupSuccessful(Data data);
    void onSignupFailed(String msg);
}
