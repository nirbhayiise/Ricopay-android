package com.np.ricopayapp.fragments;

import android.app.Dialog;
import android.content.Intent;
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
import com.np.ricopayapp.DashboardActivity;
import com.np.ricopayapp.R;
import com.np.ricopayapp.interfaces.CreateLoanCallBack;
import com.np.ricopayapp.respositrys.LoanFormRepositry;
import com.np.ricopayapp.services.SingletonObjectAccess;
import com.np.ricopayapp.utils.UserPreference;

import java.util.ArrayList;
import java.util.List;


public class FragmentLoanCreate extends Fragment implements CreateLoanCallBack {
    List<String> paymentmodeList;
    List<String> paymentmodelistId;

    List<String> identifyLs;
  //  List<String> identifyIdLs;

    String dataSelectedId="";
    String paytypVal="";
    MaterialSpinner paytyp,id_card;
    CreateLoanCallBack _this;
    TextView loanform,loanbtn;
    EditText acc_no,amount ,purpose,bvn,bankacc_name,bankacc_no,bank_name,pay_type,int_rate,nationality,occupation,employment,officeaddress,idcard_no,staff_id;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_loan,container,false);
        _this=this;
        paytyp=view.findViewById(R.id.paytyp);
        paymentmodeList = new ArrayList<String>();
        paymentmodelistId = new ArrayList<String>();
        paymentmodeList.add("Cash");
        paymentmodeList.add("Online");
        paymentmodeList.add("Bank");
        paymentmodelistId.add("1");
        paymentmodelistId.add("2");
        paymentmodelistId.add("3");


        identifyLs = new ArrayList<String>();
        //identifyIdLs = new ArrayList<String>();
        identifyLs.add("Passport");
        identifyLs.add("Drivers License");
        identifyLs.add("National ID Card");
        identifyLs.add("Third party ID Card");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, paymentmodeList);
        // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        paytyp.setAdapter(adapter);
        paytyp.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                paytypVal=paymentmodeList.get(position);
            }
        });
        loanform=getActivity().findViewById(R.id.loanform);
        acc_no=view.findViewById(R.id.acc_no);
        amount=view.findViewById(R.id.amount);
        purpose=view.findViewById(R.id.purpose);
        bvn=view.findViewById(R.id.bvn);
        bankacc_name=view.findViewById(R.id.bankacc_name);
        bankacc_no=view.findViewById(R.id.bankacc_no);
        bank_name=view.findViewById(R.id.bank_name);
        pay_type=view.findViewById(R.id.pay_type);
        int_rate=view.findViewById(R.id.int_rate);
        nationality=view.findViewById(R.id.nationality);
        occupation=view.findViewById(R.id.occupation);
        employment=view.findViewById(R.id.employment);
        officeaddress=view.findViewById(R.id.officeaddress);
        id_card=view.findViewById(R.id.id_card);
        idcard_no=view.findViewById(R.id.idcard_no);
        staff_id=view.findViewById(R.id.staff_id);
        loanbtn=view.findViewById(R.id.loanbtn);

        loanform.setText("Loan Form");

        loanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoanFormRepositry.getInstance().loanCreate(acc_no.getText().toString(),amount.getText().toString(),purpose.getText().toString(),bvn.getText().toString(),bankacc_name.getText().toString(),bankacc_no.getText().toString(),bank_name.getText().toString(),paytypVal,int_rate.getText().toString(),nationality.getText().toString(),occupation.getText().toString(),employment.getText().toString(),officeaddress.getText().toString(),dataSelectedId,idcard_no.getText().toString(), UserPreference.getUid(getActivity()),getActivity(),_this);

            }
        });
        ArrayAdapter<String> adapteridCard = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, identifyLs);
        // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        id_card.setAdapter(adapteridCard);
        id_card.setOnItemSelectedListener((view1, position, id, item) -> {

            dataSelectedId=identifyLs.get(position);
        });
        return view;
    }

    @Override
    public void onLoanCreateSuccess(Integer loanId) {
        SingletonObjectAccess.DialogMessage1(new Dialog(getActivity()),getActivity(),"Loan has been sucessfully applyed your loan id "+loanId);

    }

    @Override
    public void onLoanFailed(String errorMessage) {
        SingletonObjectAccess.DialogMessage(new Dialog(getActivity()),getActivity()," "+errorMessage);

    }
}
