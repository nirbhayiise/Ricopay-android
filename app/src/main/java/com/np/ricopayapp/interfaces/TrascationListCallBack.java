package com.np.ricopayapp.interfaces;




import com.np.ricopayapp.responsemodels.transaction_list_response.Datum;

import java.util.List;

public interface TrascationListCallBack {
   void onTrascationList(List<Datum> data);
   void noDataFound(String data);
}
