package com.np.ricopayapp.interfaces;

import com.np.ricopayapp.responsemodels.state_list.Datum;

import java.util.List;

public interface SpinnerData_CallBack {
   void onStateList(List<Datum> data);
}
