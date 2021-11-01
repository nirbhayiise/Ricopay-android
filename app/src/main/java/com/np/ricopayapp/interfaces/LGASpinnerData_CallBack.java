package com.np.ricopayapp.interfaces;


import com.np.ricopayapp.responsemodels.lga_list.Datum;

import java.util.List;

public interface LGASpinnerData_CallBack {
   void onLGAList(List<Datum> data);
}
