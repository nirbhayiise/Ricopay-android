package com.np.ricopayapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.np.ricopayapp.R;

public class FragmentStaffHome extends Fragment {
    private LinearLayout custreg,loanbtn,easypayapplication,diposit,trasbtn;
    TextView headername;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_staff_home, container, false);
        custreg=view.findViewById(R.id.custreg);
        loanbtn=view.findViewById(R.id.loanbtn);
        easypayapplication=view.findViewById(R.id.easypayapplication);
        diposit=view.findViewById(R.id.diposit);
        trasbtn=view.findViewById(R.id.trasbtn);
        trasbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(new FragmentTranscationList());
            }
        });
        easypayapplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(new FragmentEasypay_Application());
            }
        });
        custreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(new FragmentCustomerRegistration());
            }
        });
        loanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(new FragmentLoanCreate());
            }
        });
        diposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(new FragmentCustomerDepositService());
            }
        });
        return view;
    }

    private void changeFragment(Fragment fragment){
        if (fragment != null){
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
            fragmentTransaction.replace(R.id.details_fragment, fragment);
            fragmentTransaction.commit();
        }
    }
}
