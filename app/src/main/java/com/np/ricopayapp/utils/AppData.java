package com.np.ricopayapp.utils;

import android.app.Application;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.np.ricopayapp.R;

import java.util.List;

public class AppData extends Application {
//
    String filename="";
    public  static AppData objectInstance=null;
    public static AppData getAppDataInstance(Context cnt)
    {
        if(objectInstance==null)
        {
            objectInstance=(AppData)cnt.getApplicationContext();
        }
        return objectInstance;
    }


    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public  void  dialogsInfo(Context _this, String msg)
    {


        final Dialog dialog = new Dialog(_this);
        // Include dialog.xml file
        dialog.setContentView(R.layout.custom_dialog);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        TextView msgTxt =dialog.findViewById(R.id.msg);
        msgTxt.setText(msg);
        TextView okbtn=dialog.findViewById(R.id.okbtn);
        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        // Set dialog title
       // dialog.setTitle("Info");



        dialog.show();



    }
    public ProgressDialog getInitProgressBar(Context _this)
    {
        ProgressDialog pd = null;
        if(pd==null){
            pd=new ProgressDialog(_this);
        }
        return pd;
    }
    public void startPd(ProgressDialog pd)
    {
        pd.setMessage("Please wait...");
        pd.show();
    }
    public void endPd(ProgressDialog pd)
    {

        pd.dismiss();
    }

}
