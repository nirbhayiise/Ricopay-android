package com.np.ricopayapp.interfaces;




import com.np.ricopayapp.responsemodels.account_type_response_data.Datum;

import java.util.List;

public interface AccountTypeList_CallBack {
   void onAccoutTypeList(List<Datum> data);
}
