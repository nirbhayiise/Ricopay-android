package com.np.ricopayapp.interfaces;



public interface DepositCallBack {
    void onDepositSuccess(String  data);
    void onDepositFailed(String msg);

}
