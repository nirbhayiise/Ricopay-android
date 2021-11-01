package com.np.ricopayapp.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.np.ricopayapp.R;
import com.np.ricopayapp.adpt.HistoryListAdapter;
import com.np.ricopayapp.adpt.TranscationListAdapter;
import com.np.ricopayapp.interfaces.CustListCallback;
import com.np.ricopayapp.interfaces.TrascationListCallBack;
import com.np.ricopayapp.responsemodels.cut_regist_list_resp.Datum;
import com.np.ricopayapp.respositrys.CustListListingRepositry;
import com.np.ricopayapp.respositrys.TrascationListRepositry;
import com.np.ricopayapp.utils.UserPreference;

import java.util.Calendar;
import java.util.List;

public class FragmentTranscationList extends Fragment implements TrascationListCallBack {

    private int mYear, mMonth, mDay, mHour, mMinute;
    TextView headername;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    EditText dt;
    TextView searchbtn,nodata;
    TrascationListCallBack tsacclbck;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.transcation_list, container, false);
        tsacclbck=this;
        headername = getActivity().findViewById(R.id.loanform);
        headername.setText("Transcation List");
        mRecyclerView = (RecyclerView)view.findViewById(R.id.trasls);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        dt=view.findViewById(R.id.dt);
        searchbtn=view.findViewById(R.id.searchbtn);
        nodata=view.findViewById(R.id.nodata);
        TrascationListRepositry.getInstance().getranscationLs(UserPreference.getUid(getActivity()),"",getActivity(),tsacclbck);
      //  CustListListingRepositry.getInstance().getCusLsList( UserPreference.getUid(getActivity()),getActivity(),this);
        dt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                dt.setText(year  + "-" +dayOfMonth + "-" + (monthOfYear + 1));

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrascationListRepositry.getInstance().getranscationLs(UserPreference.getUid(getActivity()),dt.getText().toString(),getActivity(),tsacclbck);
            }
        });
        return view;
    }



    @Override
    public void onTrascationList(List<com.np.ricopayapp.responsemodels.transaction_list_response.Datum> data) {
        nodata.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mAdapter = new TranscationListAdapter(getActivity(),data);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void noDataFound(String data) {
        nodata.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }
}