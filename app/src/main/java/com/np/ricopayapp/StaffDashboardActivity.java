package com.np.ricopayapp;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.np.ricopayapp.fragments.FragmentCustProfile;
import com.np.ricopayapp.fragments.FragmentCustomerList;
import com.np.ricopayapp.fragments.FragmentCustomerRegistration;
import com.np.ricopayapp.fragments.FragmentEasypay_Application;
import com.np.ricopayapp.fragments.FragmentHome;
import com.np.ricopayapp.fragments.FragmentLoanCreate;
import com.np.ricopayapp.fragments.FragmentProfile;
import com.np.ricopayapp.fragments.FragmentStaffHome;
import com.np.ricopayapp.fragments.FragmentTranscationList;
import com.np.ricopayapp.utils.UserPreference;

import java.util.List;

public class StaffDashboardActivity extends AppCompatActivity implements View.OnClickListener {

    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };
    private final int Delay_Time = 200;
    ImageView navbtnImg;
    LinearLayout navbtn;
    TextView custList,headername,name,navhome,navprofile,navshopping,navcat,navcart,navmyorder,custprofiles,navloanform,navloanpayback,
            navloanlist,navsavingform,navdepositform,navwithdrawel,navfixedsaving,navdepositlist,logout;
    DrawerLayout drawer;

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }


    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_activity_main);
        UserPreference.init(StaffDashboardActivity.this);
        verifyStoragePermissions(StaffDashboardActivity.this);
        requestMultiplePermissions();
        headername=findViewById(R.id.loanform);
        headername.setText("Staff Home");
        name = findViewById(R.id.name);
        navbtn = findViewById(R.id.navbtn);
        navbtnImg = findViewById(R.id.navbtnImg);
        navhome = findViewById(R.id.navhome);
        navprofile = findViewById(R.id.navprofile);
        navshopping = findViewById(R.id.navshopping);
        navcat = findViewById(R.id.navcat);
        navcart = findViewById(R.id.navcart);
        navmyorder = findViewById(R.id.navmyorder);
        custList = findViewById(R.id.custList);
        custprofiles = findViewById(R.id.custprofiles);

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
        custprofiles.setOnClickListener(this);

        logout.setOnClickListener(this);
        drawer.setOnClickListener(this);
        custList.setOnClickListener(this);

        name.setText(UserPreference.getNames(StaffDashboardActivity.this));
    }
    private void  requestMultiplePermissions(){
        Dexter.withActivity(this)
                .withPermissions(

                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            //  Toast.makeText(getApplicationContext(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings

                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions,
                                                                   PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
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

                headername.setText("Staff Home");
                changeFragment(new FragmentStaffHome());
                new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        closeDrawer();
                    }
                },Delay_Time);
                break;

            case R.id.navprofile:
                headername.setText("Customer Registration");
                changeFragment(new FragmentCustomerRegistration());
                new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        closeDrawer();
                    }
                },Delay_Time);
                break;

            case R.id.custList:
                headername.setText("Staff Home");
                changeFragment(new FragmentCustomerList());
                new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        closeDrawer();
                    }
                },Delay_Time);
                break;

            case R.id.navcat:
               changeFragment(new FragmentTranscationList());
                new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        closeDrawer();
                    }
                },Delay_Time);
                break;

            case R.id.navcart:
              changeFragment(new FragmentEasypay_Application());
                new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        closeDrawer();
                    }
                },Delay_Time);
                break;
            case R.id.navmyorder:
                changeFragment(new FragmentLoanCreate());
                new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        closeDrawer();
                    }
                },Delay_Time);
                break;

            case R.id.custprofiles:
                changeFragment(new FragmentCustProfile());
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
                        StaffDashboardActivity.this);
                builder.setTitle("Log Out");
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setMessage("Do you want to logout from application?");
                builder.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                UserPreference.logOut();
                                startActivity(new Intent(StaffDashboardActivity.this, LoginActivity.class));

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
