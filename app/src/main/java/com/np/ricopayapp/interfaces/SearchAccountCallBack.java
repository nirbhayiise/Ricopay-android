package com.np.ricopayapp.interfaces;

public interface SearchAccountCallBack {
    void onFoundName(String name);

    void NameNotFound(String errorMessage);
}
