package com.np.ricopayapp.interfaces;




public interface CustomerRegisterCallback {
    void onRegCustomerRegisterSuccessful(String data);
    void onCustomerRegisterUserFailed(String msg);

}
