package com.np.ricopayapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import com.np.ricopayapp.interfaces.RegisterUserCallBack;
import com.np.ricopayapp.interfaces.SignupCallBack;
import com.np.ricopayapp.interfaces.StoreListCallback;
import com.np.ricopayapp.responsemodels.register_response.Data;
import com.np.ricopayapp.responsemodels.store_response.Datum;
import com.np.ricopayapp.respositrys.SignupRepositry;
import com.np.ricopayapp.services.SingletonObjectAccess;
import com.np.ricopayapp.utils.UserPreference;

import java.util.List;

public class RegisterUserActivity extends AppCompatActivity implements RegisterUserCallBack {


    RegisterUserCallBack rCallback;
    TextView signbtn;
    TextView names,mobile,username,password;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        UserPreference.init(RegisterUserActivity.this);
        signbtn=findViewById(R.id.signbtn);
        rCallback=this;
        //Editext
        names=findViewById(R.id.names);
        mobile=findViewById(R.id.mobile);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);

        signbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               SignupRepositry.getInstance().registerInvok(names.getText().toString(),mobile.getText().toString(),username.getText().toString(),password.getText().toString(),RegisterUserActivity.this,rCallback);
            }
        });


    }


    @Override
    public void onRegisterUserSuccessful(Data data) {
      
        UserPreference.setAuth_names(data.getAuthNames());
        UserPreference.setAuth_mobile(data.getAuthMobile());
        UserPreference.setAuth_username(data.getAuthUsername());
        UserPreference.setAuth_password(data.getAuthPassword());
        SingletonObjectAccess.DialogMessageRegister(new Dialog(RegisterUserActivity.this),RegisterUserActivity.this,"New User  has been created sucessfully, You can login ");
       /* startActivity(new Intent(RegisterUserActivity.this,DashboardActivity.class));
        finish();*/
    }

    @Override
    public void onRegisterUserFailed(String msg) {
        SingletonObjectAccess.DialogMessage(new Dialog(RegisterUserActivity.this),RegisterUserActivity.this,msg);
        Toast.makeText(this, ""+msg, Toast.LENGTH_SHORT).show();
    }
}
