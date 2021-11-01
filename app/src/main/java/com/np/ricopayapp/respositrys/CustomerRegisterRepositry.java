package com.np.ricopayapp.respositrys;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.np.ricopayapp.interfaces.CreateLoanCallBack;
import com.np.ricopayapp.interfaces.CustomerRegisterCallback;
import com.np.ricopayapp.responsemodels.info_reponse.InfoWrapper;
import com.np.ricopayapp.services.APIs;
import com.np.ricopayapp.services.SingletonObjectAccess;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CustomerRegisterRepositry {
    public static CustomerRegisterRepositry signupObject=null;
    public static CustomerRegisterRepositry getInstance(){
        if (signupObject==null)
        {
            signupObject=new CustomerRegisterRepositry();
        }
        return signupObject;
    }

    private CustomerRegisterRepositry() {


    }
    public void customerRegsiterInvok(  String auth_id,String
                                                         acc_no,
                                        String names,
                                        String marital_status,
                                        String gender,
                                        String  dob,
                                        String  lga,
                                        String   c_address,
                                        String    city,
                                        String   reg_state,
                                        String   c_state,
                                        String    mobile,
                                        String    email,
                                        String   acc_type,
                                        String    pay_mode,
                                        String   amount,
                                        String  reg_fee,
                                        String  image,
                                        String  staff_id,
                                        String   acc_officer_id,
                                        String  branch_id, Context cnt, CustomerRegisterCallback cl_callback){
       ProgressDialog pd=new ProgressDialog(cnt);
        pd.setMessage("Please wait...");
        pd.show();
        APIs apiService = SingletonObjectAccess.getApiService();
        Call<InfoWrapper> call = apiService.custRegistrations(auth_id,
                acc_no,
                names,
                marital_status,
                gender,
                dob,
                lga,
                c_address,
                city,
                reg_state,
                c_state,
                mobile,
                email,
                acc_type,
                pay_mode,
                amount,
                reg_fee,
                image,
                staff_id,
                acc_officer_id,
                branch_id);
        call.enqueue(new Callback<InfoWrapper>() {
            @Override
            public void onResponse(Call<InfoWrapper> call, Response<InfoWrapper> response) {
                pd.dismiss();
                if(response.body().getResponse().getStatus().equals("1")){
                    cl_callback.onRegCustomerRegisterSuccessful(""+response.body().getResponse().getLoanID());
                }else{
                    cl_callback.onCustomerRegisterUserFailed(""+response.body().getResponse().getMessage());
                }
            }

            @Override
            public void onFailure(Call<InfoWrapper> call, Throwable t) {

                Toast.makeText(cnt, ""+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }



}
