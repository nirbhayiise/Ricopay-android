package com.np.ricopayapp.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.np.ricopayapp.LoginActivity;
import com.np.ricopayapp.R;
import com.np.ricopayapp.interfaces.AccountTypeList_CallBack;
import com.np.ricopayapp.interfaces.CustomerRegisterCallback;
import com.np.ricopayapp.interfaces.DepositCallBack;
import com.np.ricopayapp.interfaces.SearchAccountCallBack;
import com.np.ricopayapp.interfaces.StaffList_CallBack;
import com.np.ricopayapp.responsemodels.staff_list_response.Datum;
import com.np.ricopayapp.respositrys.AccountTypeListRepositry;
import com.np.ricopayapp.respositrys.DepositRepositry;
import com.np.ricopayapp.respositrys.EasyPayApplicationRepositry;
import com.np.ricopayapp.respositrys.StaffListRepositry;
import com.np.ricopayapp.services.SingletonObjectAccess;
import com.np.ricopayapp.utils.UserPreference;

import java.util.ArrayList;
import java.util.List;

public class FragmentCustomerDepositService extends Fragment implements StaffList_CallBack, CustomerRegisterCallback, AccountTypeList_CallBack, SearchAccountCallBack, DepositCallBack {


    String paymode="";

    List<String> accounttypels;
    List<String> accountypeidls;
    String selectAccounttype="";
    List<String> paymentmodeList;
    List<String> paymentmodelistId;
    MaterialSpinner paytyp,acc_typ;
    EditText accountno,amount;
    TextView headername,depositbtn,searchbtn,holdername;
    CustomerRegisterCallback crecallbck;
    LinearLayout holderlay;
    SearchAccountCallBack srchcllbck;
    DepositCallBack dpscallbck;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cust_diposit_service, container, false);
        UserPreference.init(getActivity());
        crecallbck=this;
        srchcllbck=this;
        dpscallbck=this;
        headername = getActivity().findViewById(R.id.loanform);
        headername.setText("Customer diposit");
        StaffListRepositry.getInstance().getstaffLs(getActivity(),this);
        depositbtn=view.findViewById(R.id.depositbtn);
        paytyp=view.findViewById(R.id.paymode);
        acc_typ=view.findViewById(R.id.acc_typ);
        accountno=view.findViewById(R.id.accountno);
        searchbtn=view.findViewById(R.id.searchbtn);
        amount=view.findViewById(R.id.amount);
        holderlay=view.findViewById(R.id.holderlay);
        holdername=view.findViewById(R.id.holdername);


        accounttypels = new ArrayList<String>();
        accountypeidls = new ArrayList<String>();
        paymentmodeList = new ArrayList<String>();
        paymentmodelistId = new ArrayList<String>();
        paymentmodeList.add("Deposit");
        paymentmodeList.add("Withdraw");
        paymentmodeList.add("Others");
        paymentmodelistId.add("1");
        paymentmodelistId.add("2");
        paymentmodelistId.add("3");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, paymentmodeList);
        // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paytyp.setAdapter(adapter);
        paytyp.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                paymode=paymentmodeList.get(position);
            }
        });

        acc_typ.setOnItemSelectedListener((view1, position, id, item) -> {
            selectAccounttype=accountypeidls.get(position);
        });

        AccountTypeListRepositry.getInstance().getAcounttype(getActivity(),this);
        depositbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DepositRepositry.getInstance().invokDeposit(accountno.getText().toString(),amount.getText().toString(),selectAccounttype,paymode, UserPreference.getUid(getActivity()),getActivity(),dpscallbck);
            }
        });
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            DepositRepositry.getInstance().searchNameAccount(accountno.getText().toString(),getActivity(),srchcllbck);
            }
        });
        return view;
    }

    @Override
    public void onLGAList(List<Datum> data) {


    }

    @Override
    public void onRegCustomerRegisterSuccessful(String data) {
        SingletonObjectAccess.DialogMessage1(new Dialog(getActivity()),getActivity(),""+data);
    }

    @Override
    public void onCustomerRegisterUserFailed(String msg) {
        SingletonObjectAccess.DialogMessage(new Dialog(getActivity()),getActivity(),""+msg);
    }

    @Override
    public void onAccoutTypeList(List<com.np.ricopayapp.responsemodels.account_type_response_data.Datum> data) {
        for (int index = 0; index < data.size(); index++) {
            accounttypels.add(data.get(index).getName());
            accountypeidls.add(data.get(index).getId());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, accounttypels);
        // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        acc_typ.setAdapter(adapter);
    }

    @Override
    public void onFoundName(String name) {
        holderlay.setVisibility(View.VISIBLE);
        holdername.setBackgroundResource(R.drawable.dotted_radius);
        holdername.setText(name);
        holdername.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.checked, 0);
     //   SingletonObjectAccess.DialogMessage(new Dialog(getActivity()),getActivity(),name);
    }

    @Override
    public void NameNotFound(String errorMessage) {
        holderlay.setVisibility(View.VISIBLE);
        holdername.setBackgroundResource(R.drawable.red_dotted_radius);
        holdername.setText("Account/Mobile Number Not Exist");
        holdername.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.remove, 0);
        SingletonObjectAccess.DialogMessage(new Dialog(getActivity()),getActivity(),errorMessage);
    }

    @Override
    public void onDepositSuccess(String data) {
        SingletonObjectAccess.DialogMessage1(new Dialog(getActivity()),getActivity(),data);
    }

    @Override
    public void onDepositFailed(String msg) {

    }
}
