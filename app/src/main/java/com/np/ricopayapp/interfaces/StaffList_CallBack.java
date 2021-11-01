package com.np.ricopayapp.interfaces;




import com.np.ricopayapp.responsemodels.staff_list_response.Datum;

import java.util.List;

public interface StaffList_CallBack {
   void onLGAList(List<Datum> data);
}
