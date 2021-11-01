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
import com.np.ricopayapp.responsemodels.login_user.Data;
import com.np.ricopayapp.respositrys.LoginRepositry;
import com.np.ricopayapp.utils.UserPreference;

public class LoginActivity extends AppCompatActivity implements LoginCallBack {

    LoginCallBack lcallback;
    EditText uname,pass;
    TextView loginbtn,resgitserbtn,stafflogin;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        lcallback=this;
        UserPreference.init(LoginActivity.this);
        stafflogin=findViewById(R.id.stafflogin);
        loginbtn=findViewById(R.id.loginbtn);
        resgitserbtn=findViewById(R.id.resgitserbtn);
        uname=findViewById(R.id.uname);
        pass=findViewById(R.id.pass);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginRepositry.getInstance().LoginInvok(uname.getText().toString(),pass.getText().toString(),LoginActivity.this,lcallback);

            }
        });
        stafflogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,StaffLoginActivity.class));
                //finish();
            }
        });
        resgitserbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterUserActivity.class));
                //finish();
            }
        });
    }

    @Override
    public void onLoginSuccessful(Data data) {
        UserPreference.setAuth_names(data.getAuthNames());
        UserPreference.setAuth_mobile(data.getAuthMobile());
        UserPreference.setAuth_username(data.getAuthNames());
        UserPreference.setAuth_password(data.getAuthNames());

        startActivity(new Intent(LoginActivity.this,DashboardActivity.class));
        finish();
    }

    @Override
    public void onLoginFailed(String msg) {
        Toast.makeText(this, ""+msg, Toast.LENGTH_SHORT).show();
    }
}
