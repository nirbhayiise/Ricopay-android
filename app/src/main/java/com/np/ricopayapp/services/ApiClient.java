package com.np.ricopayapp.services;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.net.NetworkInterface;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

   /* public static final String BASE_URL = BuildConfig.BASE_URL;*/
    public static final String BASE_URL_IMAGE="http://43.225.52.33/~onlineinterview/tesing/onlineinterview";
   public static final String BASE_URL ="https://onielng.com/eBiz/apis/";
   private static Retrofit retrofit = null;
    private static Retrofit retrofitOnly = null;

   public static final long NETWORK_CALL_TIMEOUT = 60;



    public static NetworkInterface create() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.MINUTES)
                .writeTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.SECONDS)
                .build();

        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit.create(NetworkInterface.class);
    }

    public static NetworkInterface RetrofitInstance(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if(retrofitOnly==null) {
            retrofitOnly = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return  retrofitOnly.create(NetworkInterface.class);
    }
}
