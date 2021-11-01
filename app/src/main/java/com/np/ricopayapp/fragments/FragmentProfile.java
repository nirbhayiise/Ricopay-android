package com.np.ricopayapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.np.ricopayapp.R;
import com.np.ricopayapp.utils.UserPreference;


public class FragmentProfile extends Fragment {


    EditText Username,Mobile,pass;
    TextView userna,headername;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);

        userna=view.findViewById(R.id.userna);
        Username=view.findViewById(R.id.Username);
        Mobile=view.findViewById(R.id.Mobile);
        pass=view.findViewById(R.id.pass);
        userna.setText(UserPreference.getAuth_names(getActivity()));
        Username.setText(UserPreference.getAuth_username(getActivity()));
        Mobile.setText(UserPreference.getAuth_mobile(getActivity()));
        pass.setText(UserPreference.getAuth_password(getActivity()));
        headername=getActivity().findViewById(R.id.loanform);
        headername.setText("Profile");
        return view;
    }
}
