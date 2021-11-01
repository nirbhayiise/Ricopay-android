package com.np.ricopayapp.respositrys;

import android.app.ProgressDialog;
import android.content.Context;

import com.np.ricopayapp.interfaces.LoginCallBack;
import com.np.ricopayapp.interfaces.UploadImageCallBack;
import com.np.ricopayapp.responsemodels.login_user.LoginUserWrapper;
import com.np.ricopayapp.responsemodels.upload_image.UploadImageWrapper;
import com.np.ricopayapp.services.APIs;
import com.np.ricopayapp.services.SingletonObjectAccess;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.np.ricopayapp.services.PrefUtils.cnt;


public class UploadImageRepositry {
    public static UploadImageRepositry _object=null;
    public static UploadImageRepositry getInstance(){
        if (_object==null)
        {
            _object=new UploadImageRepositry();
        }
        return _object;
    }

    private UploadImageRepositry() {


    }
    public void UploadImage(MultipartBody.Part body,  Context cnt, UploadImageCallBack uploadcall){
       ProgressDialog pd=new ProgressDialog(cnt);
        pd.setMessage("Please wait Image Uploading...");
        pd.setCancelable(false);
        pd.show();
        APIs apiService = SingletonObjectAccess.getApiService();
        Call<UploadImageWrapper> call = apiService.updateProfileRetrofit(body);
        call.enqueue(new Callback<UploadImageWrapper>() {
            @Override
            public void onResponse(Call<UploadImageWrapper> call, Response<UploadImageWrapper> response) {
                pd.dismiss();
              //  String flg=response.body().getResponse().getStatus();
                if(response.body().getSuccess())
                {

                    uploadcall.onUploadImageSuccess(response.body().getFilename());
                }else{
                    uploadcall.onUploadImageFailed(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<UploadImageWrapper> call, Throwable t) {
                pd.dismiss();
                uploadcall.onUploadImageFailed("Something went wrong"+t.toString());
            }
        });
    }



}
