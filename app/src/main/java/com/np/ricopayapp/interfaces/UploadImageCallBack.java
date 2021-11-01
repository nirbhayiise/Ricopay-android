package com.np.ricopayapp.interfaces;

import com.np.ricopayapp.responsemodels.login_user.Data;

public interface UploadImageCallBack {
    void onUploadImageSuccess(String filename);
    void onUploadImageFailed(String msg);
}
