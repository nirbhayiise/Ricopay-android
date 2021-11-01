package com.np.ricopayapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.np.ricopayapp.interfaces.LoginCallBack;
import com.np.ricopayapp.interfaces.StaffLoginCallBack;
import com.np.ricopayapp.responsemodels.login_user.Data;
import com.np.ricopayapp.respositrys.LoginRepositry;
import com.np.ricopayapp.respositrys.StaffLoginRepositry;
import com.np.ricopayapp.utils.UserPreference;

public class StaffLoginActivity extends AppCompatActivity implements StaffLoginCallBack {

    StaffLoginCallBack lcallback;
    EditText uname,pass;
    TextView loginbtn,resgitserbtn,custlogin;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_login_activity);
        lcallback=this;
        UserPreference.init(StaffLoginActivity.this);
        custlogin=findViewById(R.id.custlogin);
        loginbtn=findViewById(R.id.loginbtn);
        resgitserbtn=findViewById(R.id.resgitserbtn);
        uname=findViewById(R.id.uname);
        pass=findViewById(R.id.pass);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaffLoginRepositry.getInstance().LoginInvok(uname.getText().toString(),pass.getText().toString(), StaffLoginActivity.this,lcallback);

            }
        });
        custlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        resgitserbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StaffLoginActivity.this,RegisterUserActivity.class));
                finish();
            }
        });
    }



    @Override
    public void onLoginSuccessful(com.np.ricopayapp.responsemodels.staff_login.Data data) {
        UserPreference.setUid(data.getId());
        UserPreference.setNames(data.getNames());
        UserPreference.setMobile(data.getMobile());
        UserPreference.setAddress(data.getAddress());
        UserPreference.setUsername(data.getUsername());
        UserPreference.setPassword(data.getPassword());

        startActivity(new Intent(StaffLoginActivity.this,StaffDashboardActivity.class));
        finish();
    }

    @Override
    public void onLoginFailed(String msg) {
        Toast.makeText(this, ""+msg, Toast.LENGTH_SHORT).show();
    }
}
