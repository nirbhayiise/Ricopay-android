package com.np.ricopayapp.interfaces;

import com.np.ricopayapp.responsemodels.store_response.Datum;

import java.util.List;

public interface StoreListCallback {
    void onStoreSuccess(List<Datum> data);
    void onStoreFailed(String msg);
}
