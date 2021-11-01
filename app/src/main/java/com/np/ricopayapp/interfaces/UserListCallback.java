package com.np.ricopayapp.interfaces;

import com.np.ricopayapp.responsemodels.users_list.Datum;

import java.util.List;

public interface UserListCallback {
    void onUserListSuccess(List<Datum> data);
    void onUserListFailed(String msg);
}
