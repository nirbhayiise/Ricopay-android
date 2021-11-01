package com.np.ricopayapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import com.np.ricopayapp.interfaces.SignupCallBack;
import com.np.ricopayapp.interfaces.StoreListCallback;
import com.np.ricopayapp.responsemodels.authresponse.Data;
import com.np.ricopayapp.responsemodels.store_response.Datum;
import com.np.ricopayapp.respositrys.SignupRepositry;
import com.np.ricopayapp.utils.UserPreference;

import java.util.List;

public class SignupActivity extends AppCompatActivity implements SignupCallBack, StoreListCallback {

    AppCompatSpinner storespinner,right_id;
    TextView signbtn;
    TextView names,mobile,address,email,username,password,file_name,dob,id_no,
            relative1,relative_mobile1,
            relative2,relative_mobile2,guarantor,guarantor_mobile;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        UserPreference.init(SignupActivity.this);
        signbtn=findViewById(R.id.signbtn);
        storespinner=findViewById(R.id.store_id);
        right_id=findViewById(R.id.right_id);

        //Editext
        names=findViewById(R.id.names);
        mobile=findViewById(R.id.mobile);
        address=findViewById(R.id.address);
        email=findViewById(R.id.email);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        file_name=findViewById(R.id.file_name);
        dob=findViewById(R.id.dob);
        id_no=findViewById(R.id.id_no);
        relative1=findViewById(R.id.relative1);
        relative_mobile1=findViewById(R.id.relative_mobile1);
        relative2=findViewById(R.id.relative2);
        relative_mobile2=findViewById(R.id.relative_mobile2);
        guarantor=findViewById(R.id.guarantor);
        guarantor_mobile=findViewById(R.id.guarantor_mobile);

        signbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SignupRepositry.getInstance().invokSignup();
            }
        });


    }

    @Override
    public void onSignupSuccessful(Data data) {

    }

    @Override
    public void onSignupFailed(String msg) {

    }

    @Override
    public void onStoreSuccess(List<Datum> data) {
        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,data);
        storespinner.setAdapter(adapter);
    }

    @Override
    public void onStoreFailed(String msg) {

        Toast.makeText(SignupActivity.this,""+msg,Toast.LENGTH_LONG).show();
    }
}
