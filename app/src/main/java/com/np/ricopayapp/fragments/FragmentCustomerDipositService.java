package com.np.ricopayapp.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.np.ricopayapp.R;
import com.np.ricopayapp.interfaces.AccountTypeList_CallBack;
import com.np.ricopayapp.interfaces.CustomerRegisterCallback;
import com.np.ricopayapp.interfaces.StaffList_CallBack;
import com.np.ricopayapp.responsemodels.staff_list_response.Datum;
import com.np.ricopayapp.respositrys.AccountTypeListRepositry;
import com.np.ricopayapp.respositrys.EasyPayApplicationRepositry;
import com.np.ricopayapp.respositrys.StaffListRepositry;
import com.np.ricopayapp.services.SingletonObjectAccess;

import java.util.ArrayList;
import java.util.List;

public class FragmentCustomerDipositService extends Fragment implements StaffList_CallBack, CustomerRegisterCallback, AccountTypeList_CallBack {

    String staffId="";
    String paymode="";
    TextView signbtn;
    List<String> accounttypels;
    List<String> accountypeidls;
    String selectAccounttype="";

    List<String> paymentmodeList;
    List<String> paymentmodelistId;
    MaterialSpinner paytyp,acc_typ;
    EditText accountno,amount;
    TextView headername;
    CustomerRegisterCallback crecallbck;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cust_diposit_service, container, false);
        crecallbck=this;
        headername = getActivity().findViewById(R.id.loanform);
        headername.setText("Customer diposit");
        StaffListRepositry.getInstance().getstaffLs(getActivity(),this);
        signbtn=view.findViewById(R.id.signbtn);

        paytyp=view.findViewById(R.id.paymode);
        acc_typ=view.findViewById(R.id.acc_typ);

        accountno=view.findViewById(R.id.accountno);

        amount=view.findViewById(R.id.amount);

        accounttypels = new ArrayList<String>();
        accountypeidls = new ArrayList<String>();





       paymentmodeList = new ArrayList<String>();
        paymentmodelistId = new ArrayList<String>();

        paymentmodeList.add("Cash");
        paymentmodeList.add("Online");
        paymentmodeList.add("Bank");
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
}
