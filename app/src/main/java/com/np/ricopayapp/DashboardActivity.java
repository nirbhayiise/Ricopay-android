package com.np.ricopayapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.np.ricopayapp.fragments.FragmentHome;
import com.np.ricopayapp.fragments.FragmentLoanCreate;
import com.np.ricopayapp.fragments.FragmentProfile;
import com.np.ricopayapp.interfaces.LoginCallBack;
import com.np.ricopayapp.responsemodels.login_user.Data;
import com.np.ricopayapp.respositrys.LoginRepositry;
import com.np.ricopayapp.utils.UserPreference;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    private final int Delay_Time = 200;
    ImageView navbtnImg;
    LinearLayout navbtn;
    TextView name,navhome,navprofile,navshopping,navcat,navcart,navmyorder,navorderhistry,navloanform,navloanpayback,
            navloanlist,navsavingform,navdepositform,navwithdrawel,navfixedsaving,navdepositlist,logout;
    DrawerLayout drawer;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserPreference.init(DashboardActivity.this);

        name = findViewById(R.id.name);
        navbtn = findViewById(R.id.navbtn);
        navbtnImg = findViewById(R.id.navbtnImg);
        navhome = findViewById(R.id.navhome);
        navprofile = findViewById(R.id.navprofile);
        navshopping = findViewById(R.id.navshopping);
        navcat = findViewById(R.id.navcat);
        navcart = findViewById(R.id.navcart);
        navmyorder = findViewById(R.id.navmyorder);
        navorderhistry = findViewById(R.id.navorderhistry);
        navloanform = findViewById(R.id.navloanform);
        navloanpayback = findViewById(R.id.navloanpayback);
        navloanlist = findViewById(R.id.navloanlist);
        navsavingform = findViewById(R.id.navsavingform);
        navdepositform = findViewById(R.id.navdepositform);
        navwithdrawel = findViewById(R.id.navwithdrawel);
        navfixedsaving = findViewById(R.id.navfixedsaving);
        navdepositlist = findViewById(R.id.navdepositlist);
        logout = findViewById(R.id.logout);
        drawer = findViewById(R.id.drawer_layout);

        navbtn.setOnClickListener(this);
        navbtnImg.setOnClickListener(this);
        navhome.setOnClickListener(this);
        navprofile.setOnClickListener(this);
        navshopping.setOnClickListener(this);
        navcat.setOnClickListener(this);
        navcart.setOnClickListener(this);
        navmyorder.setOnClickListener(this);
        navorderhistry.setOnClickListener(this);
        navloanform.setOnClickListener(this);
        navloanpayback.setOnClickListener(this);
        navloanlist.setOnClickListener(this);
        navsavingform.setOnClickListener(this);
        navdepositform.setOnClickListener(this);
        navwithdrawel.setOnClickListener(this);
        navfixedsaving.setOnClickListener(this);
        navdepositlist.setOnClickListener(this);
        logout.setOnClickListener(this);
        drawer.setOnClickListener(this);

        name.setText(UserPreference.getAuth_names(DashboardActivity.this));
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.navbtnImg:
              //  Toast.makeText(this, "hi open", Toast.LENGTH_SHORT).show();
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }else {
                    drawer.openDrawer(GravityCompat.START);
                }
                break;
            case R.id.navhome:
                changeFragment(new FragmentHome());
                new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        closeDrawer();
                    }
                },Delay_Time);
                break;

            case R.id.navprofile:
                changeFragment(new FragmentProfile());
                new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        closeDrawer();
                    }
                },Delay_Time);
                break;

            case R.id.navshopping:
                changeFragment(new FragmentHome());
                new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        closeDrawer();
                    }
                },Delay_Time);
                break;

            case R.id.navcat:
                changeFragment(new FragmentHome());
                new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        closeDrawer();
                    }
                },Delay_Time);
                break;

            case R.id.navcart:
                changeFragment(new FragmentHome());
                new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        closeDrawer();
                    }
                },Delay_Time);
                break;
            case R.id.navmyorder:
                changeFragment(new FragmentHome());
                new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        closeDrawer();
                    }
                },Delay_Time);
                break;
            case R.id.navorderhistry:
                changeFragment(new FragmentHome());
                new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        closeDrawer();
                    }
                },Delay_Time);
                break;
            case R.id.navloanform:
                changeFragment(new FragmentLoanCreate());
                new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        closeDrawer();
                    }
                },Delay_Time);
                break;
            case R.id.navloanpayback:
                changeFragment(new FragmentHome());
                new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        closeDrawer();
                    }
                },Delay_Time);
                break;
            case R.id.navloanlist:
                changeFragment(new FragmentHome());
                new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        closeDrawer();
                    }
                },Delay_Time);
                break;
            case R.id.navsavingform:
                changeFragment(new FragmentHome());
                new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        closeDrawer();
                    }
                },Delay_Time);
                break;
            case R.id.navdepositform:
                changeFragment(new FragmentHome());
                new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        closeDrawer();
                    }
                },Delay_Time);
                break;
            case R.id.navwithdrawel:
                changeFragment(new FragmentHome());
                new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        closeDrawer();
                    }
                },Delay_Time);
                break;

            case R.id.navfixedsaving:
                changeFragment(new FragmentHome());
                new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        closeDrawer();
                    }
                },Delay_Time);
                break;
            case R.id.navdepositlist:
                changeFragment(new FragmentHome());
                new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        closeDrawer();
                    }
                },Delay_Time);
                break;
            case R.id.logout:
                closeDrawer();
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        DashboardActivity.this);
                builder.setTitle("Log Out");
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setMessage("Do you want to logout from application?");
                builder.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                UserPreference.logOut();
                                startActivity(new Intent(DashboardActivity.this, LoginActivity.class));

                                finish();

                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.show();
                break;

        }
    }

    private void changeFragment(Fragment fragment){
        if (fragment != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
            fragmentTransaction.replace(R.id.details_fragment, fragment);
            fragmentTransaction.commit();
        }
    }

    private void closeDrawer(){
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else {
            drawer.openDrawer(GravityCompat.START);
        }
    }


}
