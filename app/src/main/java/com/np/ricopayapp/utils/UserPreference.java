package com.np.ricopayapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;

public class UserPreference {

    private static SharedPreferences preferences;

    private static final int BUFFER_SIZE = 1024 * 2;
    private static final String IMAGE_DIRECTORY = "/buddystuddy";
    private static final String auth_names = "auth_names";
    private static final String auth_mobile = "auth_mobile";
    private static final String auth_username = "auth_username";
    private static final String auth_password = "auth_password";
    private static final String auth_created = "auth_created";
    private static final String auth_status = "auth_status";

// staff pref.

    private static final String uid="uid";
    private static final String names="names";
    private static final String mobile="mobile";
    private static final String email="email";
    private static final String address="address";
    private static final String username="username";
    private static final String password="password";
    private static final String right_id="right_id";



    private static final String MAIN_URL="mainurl";
    private UserPreference() {

    }
    public static void init(Context context) {
        if (preferences == null)
            preferences = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
    }

    public static void setAuth_names(String nam) {
        preferences.edit().putString(auth_names, nam).apply();
    }

    public static void setAuth_mobile(String mobile) {
        preferences.edit().putString(auth_mobile, mobile).apply();
    }

    public static void setAuth_username(String param) {
        preferences.edit().putString(auth_username, param).apply();
    }
    public static void setAuth_password(String pas) {
        preferences.edit().putString(auth_password, pas).apply();
    }


    public static String getAuth_names(Context context) {
        if (preferences == null)
            preferences = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
        return preferences.getString(auth_names, "");
    }

    public static String getAuth_mobile(Context context) {
        if (preferences == null)
            preferences = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
        return preferences.getString(auth_mobile, "");
    }

    public static String getAuth_username(Context context) {
        if (preferences == null)
            preferences = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
        return preferences.getString(auth_username, "");
    }

    public static String getAuth_password(Context context) {
        if (preferences == null)
            preferences = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
        return preferences.getString(auth_password, "");
    }

    public static String getAuth_created(Context context) {
        if (preferences == null)
            preferences = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
        return preferences.getString(auth_status, "");
    }

    public static String getAuth_status(Context context) {
        if (preferences == null)
            preferences = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
        return preferences.getString(auth_status, "");
    }

    public static void logOut() {
        preferences.edit().clear().apply();
    }

    public static SharedPreferences getPreferences() {
        return preferences;
    }

    public static void setPreferences(SharedPreferences preferences) {
        UserPreference.preferences = preferences;
    }
// staff data set get

    public static void setNames(String nam) {
        preferences.edit().putString(names, nam).apply();
    }

    public static void setUid(String nam) {
        preferences.edit().putString(uid, nam).apply();
    }
    public static void setMobile(String nam) {
        preferences.edit().putString(mobile, nam).apply();
    }
    public static void setEmail(String nam) {
        preferences.edit().putString(email, nam).apply();
    }
    public static void setAddress(String nam) {
        preferences.edit().putString(address, nam).apply();
    }
    public static void setUsername(String nam) {
        preferences.edit().putString(username, nam).apply();
    }
    public static void setPassword(String nam) {
        preferences.edit().putString(password, nam).apply();
    }
    public static String getNames(Context context) {

        if (preferences == null)
            preferences = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
        return preferences.getString(names, "");


    }

    public static String getUid(Context context) {

        if (preferences == null)
            preferences = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
        return preferences.getString(uid, "");


    }

    public static String getMobile(Context context) {

        if (preferences == null)
            preferences = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
        return preferences.getString(mobile, "");

    }

    public static String getEmail(Context context) {

        if (preferences == null)
            preferences = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
        return preferences.getString(email, "");
    }

    public static String getAddress(Context context) {

        if (preferences == null)
            preferences = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
        return preferences.getString(address, "");
    }

    public static String getUsername(Context context) {

        if (preferences == null)
            preferences = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
        return preferences.getString(username, "");
    }

    public static String getPassword(Context context) {

        if (preferences == null)
            preferences = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
        return preferences.getString(password, "");
    }

    public static String getRight_id(Context context) {

        if (preferences == null)
            preferences = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
        return preferences.getString(right_id, "");
    }

    // file upload utils
    public static String getFilePathFromURI(Context context, Uri contentUri, String type) {
        //copy file and send new file path

        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }
        File copyFile;
        if(type.equals("v")) {
            copyFile= new File(wallpaperDirectory + File.separator + Calendar.getInstance()
                    .getTimeInMillis() + ".mp4");
            // create folder if not exists
        }else{
            copyFile= new File(wallpaperDirectory + File.separator + Calendar.getInstance()
                    .getTimeInMillis() + ".mp3");
        }
        copy(context, contentUri, copyFile);
        Log.d("vPath--->",copyFile.getAbsolutePath());

        return copyFile.getAbsolutePath();

    }


    public static void copy(Context context, Uri srcUri, File dstFile) {
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(srcUri);
            if (inputStream == null) return;
            OutputStream outputStream = new FileOutputStream(dstFile);
            copystream(inputStream, outputStream);
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int copystream(InputStream input, OutputStream output) throws Exception, IOException {
        byte[] buffer = new byte[BUFFER_SIZE];

        BufferedInputStream in = new BufferedInputStream(input, BUFFER_SIZE);
        BufferedOutputStream out = new BufferedOutputStream(output, BUFFER_SIZE);
        int count = 0, n = 0;
        try {
            while ((n = in.read(buffer, 0, BUFFER_SIZE)) != -1) {
                out.write(buffer, 0, n);
                count += n;
            }
            out.flush();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                Log.e(e.getMessage(), String.valueOf(e));
            }
            try {
                in.close();
            } catch (IOException e) {
                Log.e(e.getMessage(), String.valueOf(e));
            }
        }
        return count;
    }

}
