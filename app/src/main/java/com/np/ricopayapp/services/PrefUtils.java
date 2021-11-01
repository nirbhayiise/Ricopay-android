package com.np.ricopayapp.services;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefUtils {
    /**
     * Storing API Key in shared preferences to
     * add it in header part of every retrofit request
     */
   public static SharedPreferences.Editor editor;
    public static Context cnt;
   public PrefUtils()
    {

    }
    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("APP_PREF", Context.MODE_PRIVATE);
    }

    public static void storeApiKey(Context context, String apiKey) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString("API_KEY", apiKey);
        editor.commit();
    }

    public static String getApiKey(Context context) {
        return getSharedPreferences(context).getString("API_KEY", null);
    }

    public static SharedPreferences.Editor putDataInPREF(Context cnt)
    {

        editor = getSharedPreferences(cnt).edit();
        return editor;
    }
}