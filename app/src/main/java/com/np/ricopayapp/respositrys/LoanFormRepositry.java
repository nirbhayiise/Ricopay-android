package com.np.ricopayapp.respositrys;

import android.app.ProgressDialog;
import android.content.Context;

import com.np.ricopayapp.interfaces.CreateLoanCallBack;
import com.np.ricopayapp.interfaces.LoginCallBack;
import com.np.ricopayapp.interfaces.RegisterUserCallBack;
import com.np.ricopayapp.responsemodels.info_reponse.InfoWrapper;
import com.np.ricopayapp.responsemodels.register_response.RegisterWrapper;
import com.np.ricopayapp.services.APIs;
import com.np.ricopayapp.services.SingletonObjectAccess;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoanFormRepositry {
    public static LoanFormRepositry signupObject=null;
    public static LoanFormRepositry getInstance(){
        if (signupObject==null)
        {
            signupObject=new LoanFormRepositry();
        }
        return signupObject;
    }

    private LoanFormRepositry() {


    }
    public void loanCreate(String acc_no, String amount, String purpose, String bvn, String bankacc_name, String bankacc_no, String bank_name, String pay_type, String int_rate, String nationality, String occupation, String employment, String officeaddress, String id_card, String idcard_no, String staff_id, Context cnt, CreateLoanCallBack cl_callback){
       ProgressDialog pd=new ProgressDialog(cnt);
        pd.setMessage("Please wait...");
        pd.show();
        APIs apiService = SingletonObjectAccess.getApiService();
        Call<InfoWrapper> call = apiService.createLoan(
                acc_no,amount,purpose,bvn,bankacc_name,bankacc_no,bank_name,pay_type,int_rate,nationality,occupation,employment,officeaddress,id_card,idcard_no,staff_id);
           call.enqueue(new Callback<InfoWrapper>() {
               @Override
               public void onResponse(Call<InfoWrapper> call, Response<InfoWrapper> response) {
                   pd.dismiss();
                   if(response.body().getResponse().getStatus().equals("1")){
                       cl_callback.onLoanCreateSuccess(response.body().getResponse().getLoanID());
                   }else
                   {
                       cl_callback.onLoanFailed(response.body().getResponse().getMessage());
                   }
               }

               @Override
               public void onFailure(Call<InfoWrapper> call, Throwable t) {
                   pd.dismiss();
                   cl_callback.onLoanFailed(t.toString());
               }
           });
    }



}
