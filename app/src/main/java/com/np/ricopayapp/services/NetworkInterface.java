package com.np.ricopayapp.services;


import com.np.ricopayapp.responsemodels.upload_image.UploadImageWrapper;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface NetworkInterface {



    @Multipart
    @POST("upload_customer_img.php")
    Call<UploadImageWrapper> uploadImg(
            @Part MultipartBody.Part file);


}
