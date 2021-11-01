package com.np.ricopayapp.services;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.np.ricopayapp.DashboardActivity;
import com.np.ricopayapp.LoginActivity;
import com.np.ricopayapp.R;
import com.np.ricopayapp.StaffDashboardActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created on : dec 25, 2019
 * Author     : nirbhay
 */
public class SingletonObjectAccess {

    private static Retrofit retrofit = null;
    private static Intent intentObject = null;
    private static ProgressDialog pdObject = null;
    private static ApplicationController publicObject=null;
    private static Dialog DialogObject=null;
    public  static SingletonObjectAccess objectInstance=null;

    private static HashMap<String, String> param = null;
   private SingletonObjectAccess()
    {

    }


    public static Dialog DialogObjectReturn(Context cnt)
    {
        if(DialogObject==null)
        {
            DialogObject = new Dialog(cnt);
        }
        return DialogObject;
    }


    public static APIs getApiService() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS).build();
        if (retrofit == null) {


           /* HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();*/
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(Const.BASE_URL).client(client)

                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

        }
        return retrofit.create(APIs.class);

    }
    public static ProgressDialog ProgressForParents(Context cnt)
    {
        if(pdObject==null) {
            return new ProgressDialog(cnt);
        }
        return pdObject;
    }
    public static Intent createActivityInstance(Context cnt,Class targetclass)
    {
        if(intentObject==null) {
            return new Intent(cnt, targetclass);
        }else {
            return intentObject;
        }
    }
    public static ApplicationController getGlobleOblect(Context cnt)
    {
        return (ApplicationController)cnt.getApplicationContext();
    }
    public static String getRandomNumberString() {
        long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;

        return ""+number;
   }
    public static HashMap<String,String> getParam()
    {
        if(param==null)
        {
            return   param=new HashMap<String,String>();
        }
        else{
            return param;
        }
    }
    @SuppressLint("LongLogTag")
    public static String getCompleteAddressString(Context cnt, double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(cnt, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                Log.w("My Current loction address", strReturnedAddress.toString());
            } else {
                Log.w("My Current loction address", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("My Current loction address", "Canont get Address!");
        }
        return strAdd;
    }
    public static boolean isNetworkConnected(Context cnt) {
        ConnectivityManager cm = (ConnectivityManager)cnt.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public static ProgressDialog getInitProgressBar(Context _this)
    {
        ProgressDialog pd = null;
        if(pd==null){
            pd=new ProgressDialog(_this);
        }
        return pd;
    }
    public static void startPd(ProgressDialog pd)
    {
        pd.setMessage("Please wait...");
        pd.show();
    }
    public static void endPd(ProgressDialog pd)
    {

        pd.dismiss();
    }
    public static SharedPreferences getShareprefObject(Context cnt)
    {
        SharedPreferences  sharedpreferences = cnt.getSharedPreferences("thelewala", Context.MODE_PRIVATE);
        return sharedpreferences;
    }
    public static SharedPreferences.Editor getPutVal(Context ct)
    {
        SharedPreferences.Editor editor = getShareprefObject(ct).edit();
        return editor;
    }
    public static void DialogMessage(Dialog diaog, Context cnt, String msg)
    {
         Dialog dialog1;

        if(diaog==null) {
            dialog1=new Dialog(cnt);
        }else
        {
            dialog1 =diaog;
        }
        // Include dialog.xml file
        dialog1.setContentView(R.layout.custom_dialog);
        dialog1.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        TextView msgTxt =dialog1.findViewById(R.id.msg);
        msgTxt.setText(msg);
        TextView okbtn=dialog1.findViewById(R.id.okbtn);
        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });
        // Set dialog title
        // dialog.setTitle("Info");



        dialog1.show();
    }

    public static void DialogMessage1(Dialog diaog, Context cnt, String msg)
    {
        Dialog dialog1;

        if(diaog==null) {
            dialog1=new Dialog(cnt);
        }else
        {
            dialog1 =diaog;
        }
        // Include dialog.xml file
        dialog1.setContentView(R.layout.custom_dialog);
        dialog1.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        TextView msgTxt =dialog1.findViewById(R.id.msg);
        msgTxt.setText(msg);
        TextView okbtn=dialog1.findViewById(R.id.okbtn);
        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cnt.startActivity(new Intent(cnt,StaffDashboardActivity.class));

                dialog1.dismiss();
            }
        });
        // Set dialog title
        // dialog.setTitle("Info");



        dialog1.show();
    }

    public static void DialogMessageRegister(Dialog diaog, Context cnt, String msg)
    {
        Dialog dialog1;

        if(diaog==null) {
            dialog1=new Dialog(cnt);
        }else
        {
            dialog1 =diaog;
        }
        // Include dialog.xml file
        dialog1.setContentView(R.layout.custom_dialog);
        dialog1.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        TextView msgTxt =dialog1.findViewById(R.id.msg);
        msgTxt.setText(msg);
        TextView okbtn=dialog1.findViewById(R.id.okbtn);
        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cnt.startActivity(new Intent(cnt, LoginActivity.class));

                dialog1.dismiss();
            }
        });
        // Set dialog title
        // dialog.setTitle("Info");



        dialog1.show();
    }

    public static void DialogMessageCustomerRegsit(Dialog diaog, Context cnt, String msg)
    {
        Dialog dialog1;

        if(diaog==null) {
            dialog1=new Dialog(cnt);
        }else
        {
            dialog1 =diaog;
        }
        // Include dialog.xml file
        dialog1.setContentView(R.layout.custom_dialog);
        dialog1.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        TextView msgTxt =dialog1.findViewById(R.id.msg);
        msgTxt.setText(msg);
        TextView okbtn=dialog1.findViewById(R.id.okbtn);
        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cnt.startActivity(new Intent(cnt, StaffDashboardActivity.class));

                dialog1.dismiss();
            }
        });
        // Set dialog title
        // dialog.setTitle("Info");



        dialog1.show();
    }
}
