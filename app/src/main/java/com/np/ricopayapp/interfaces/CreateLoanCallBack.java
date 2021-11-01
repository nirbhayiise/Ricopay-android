package com.np.ricopayapp.interfaces;

public interface CreateLoanCallBack {
    void onLoanCreateSuccess(Integer loanId);

    void onLoanFailed(String errorMessage);
}
