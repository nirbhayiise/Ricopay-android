package com.np.ricopayapp.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.content.CursorLoader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.np.ricopayapp.R;
import com.np.ricopayapp.adpt.HistoryListAdapter;
import com.np.ricopayapp.interfaces.CustListCallback;
import com.np.ricopayapp.interfaces.CustomerRegisterCallback;
import com.np.ricopayapp.interfaces.LGASpinnerData_CallBack;
import com.np.ricopayapp.interfaces.SpinnerData_CallBack;
import com.np.ricopayapp.interfaces.StoreListCallback;
import com.np.ricopayapp.interfaces.UploadImageCallBack;
import com.np.ricopayapp.interfaces.UserListCallback;
import com.np.ricopayapp.responsemodels.cut_regist_list_resp.Datum;
import com.np.ricopayapp.respositrys.CustListListingRepositry;
import com.np.ricopayapp.respositrys.CustomerRegisterRepositry;
import com.np.ricopayapp.respositrys.LGAListingRepositry;
import com.np.ricopayapp.respositrys.StateListingRepositry;
import com.np.ricopayapp.respositrys.StoreListingRepositry;
import com.np.ricopayapp.respositrys.UploadImageRepositry;
import com.np.ricopayapp.respositrys.UserListingRepositry;
import com.np.ricopayapp.services.Const;
import com.np.ricopayapp.services.SingletonObjectAccess;
import com.np.ricopayapp.utils.AppData;
import com.np.ricopayapp.utils.UserPreference;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class FragmentCustomerList extends Fragment implements CustListCallback {

    TextView headername;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.customer_regi_list, container, false);
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
        mAdapter = new HistoryListAdapter(getActivity(),data);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onCustListFailed(String msg) {

    }
}