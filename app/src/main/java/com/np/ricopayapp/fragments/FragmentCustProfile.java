package com.np.ricopayapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.np.ricopayapp.R;
import com.np.ricopayapp.adpt.CustProfileListAdapter;
import com.np.ricopayapp.adpt.HistoryListAdapter;
import com.np.ricopayapp.interfaces.CustListCallback;
import com.np.ricopayapp.responsemodels.cut_regist_list_resp.Datum;
import com.np.ricopayapp.respositrys.CustListListingRepositry;
import com.np.ricopayapp.utils.UserPreference;

import java.util.List;

public class FragmentCustProfile extends Fragment implements CustListCallback {

    TextView headername;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cust_prifile_list, container, false);
        headername = getActivity().findViewById(R.id.loanform);
        headername.setText("Customer List");
        mRecyclerView = (RecyclerView)view.findViewById(R.id.ctsls);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        CustListListingRepositry.getInstance().getCusLsList( UserPreference.getUid(getActivity()),getActivity(),this);
        return view;
    }

    @Override
    public void onCustListSuccess(List<Datum> data) {
        mAdapter = new CustProfileListAdapter(getActivity(),data);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onCustListFailed(String msg) {

    }
}