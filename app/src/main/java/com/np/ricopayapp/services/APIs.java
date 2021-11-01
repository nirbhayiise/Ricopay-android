package com.np.ricopayapp.services;


import com.np.ricopayapp.responsemodels.account_type_response_data.AccountTypeWrapper;
import com.np.ricopayapp.responsemodels.authresponse.AuthWrapper;
import com.np.ricopayapp.responsemodels.cut_regist_list_resp.CustListWrapper;
import com.np.ricopayapp.responsemodels.deposit_respo.DepositWrapper;
import com.np.ricopayapp.responsemodels.info_reponse.InfoWrapper;
import com.np.ricopayapp.responsemodels.lga_list.LGAWrapper;
import com.np.ricopayapp.responsemodels.login_user.LoginUserWrapper;
import com.np.ricopayapp.responsemodels.register_response.RegisterWrapper;
import com.np.ricopayapp.responsemodels.search_account_respo.SearchAccountWrapper;
import com.np.ricopayapp.responsemodels.staff_list_response.StaffListWrapper;
import com.np.ricopayapp.responsemodels.staff_login.StaffLoginWrapper;
import com.np.ricopayapp.responsemodels.state_list.StateWrapper;
import com.np.ricopayapp.responsemodels.store_response.StoreWrapper;
import com.np.ricopayapp.responsemodels.transaction_list_response.TransactionListWrapper;
import com.np.ricopayapp.responsemodels.upload_image.UploadImageWrapper;
import com.np.ricopayapp.responsemodels.users_list.UsersWrapper;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface APIs {

    @FormUrlEncoded
    @POST("Singup.php")
    Call<AuthWrapper> SignupInvok(
            @Field("names") String names,
            @Field("mobile") String mobile,
            @Field("email") String email,
            @Field("address") String address,
            @Field("username") String username,
            @Field("password") String password,
            @Field("right_id") String right_id,
            @Field("store_id") String store_id,
            @Field("store") String store,
            @Field("users_status") String users_status,
            @Field("file_name") String file_name,
            @Field("dob") String dob,
            @Field("id_no") String id_no,
            @Field("relative1") String relative1,
            @Field("relative_mobile1") String relative_mobile1,
            @Field("relative2") String relative2,
            @Field("relative_mobile2") String relative_mobile2,
            @Field("guarantor") String guarantor,
            @Field("guarantor_mobile") String guarantor_mobile);


    @POST("getservices_list.php")
    Call<StoreWrapper> storeList();

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginUserWrapper> loginuser(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("login_staff.php")
    Call<StaffLoginWrapper> loginStaff(@Field("username") String username, @Field("password") String password);


    @FormUrlEncoded
    @POST("register.php")
    Call<RegisterWrapper> registerUser(@Field("auth_names") String auth_names, @Field("auth_mobile") String auth_mobile, @Field("auth_username") String auth_username, @Field("auth_password") String auth_password);

    @FormUrlEncoded
    @POST("easy_pay_applications.php")
    Call<InfoWrapper> easypayReq(@Field("acc_no") String acc_no,
                                     @Field("staff_id") String staff_id,
                                     @Field("product") String product,
                                     @Field("acc_type") String acc_type,
                                     @Field("payment_mode") String payment_mode,
                                     @Field("amount") String amount,
                                     @Field("reg_fee") String reg_fee);
    @FormUrlEncoded
    @POST("deposit_form.php")
    Call<DepositWrapper> getDeposit(@Field("acc_no") String acc_no,
                                    @Field("amount") String amount,
                                    @Field("transaction") String transaction,
                                    @Field("easypay_type") String easypay_type,
                                    @Field("staff_id") String staff_id);

    @FormUrlEncoded
    @POST("getvalidateacc.php")
    Call<SearchAccountWrapper> findAccount(@Field("mobile") String mobile);


    @FormUrlEncoded
    @POST("create_loan_form.php")
    Call<InfoWrapper> createLoan(@Field("acc_no") String acc_no, @Field("amount") String amount, @Field("purpose") String purpose, @Field("bvn") String bvn, @Field("bankacc_name") String bankacc_name, @Field("bankacc_no") String bankacc_no, @Field("bank_name") String bank_name, @Field("pay_type") String pay_type, @Field("int_rate") String int_rate, @Field("nationality") String nationality, @Field("occupation") String occupation, @Field("employment") String employment, @Field("officeaddress") String officeaddress, @Field("id_card") String id_card, @Field("idcard_no") String idcard_no, @Field("staff_id") String staff_id);

    @FormUrlEncoded
    @POST("getlgalist.php")
    Call<LGAWrapper> getLGA(@Field("stateid") String stateid);

    @FormUrlEncoded
    @POST("get_customer_list_by_staff.php")
    Call<CustListWrapper> getCustList(@Field("sid") String stateid);

    @FormUrlEncoded
    @POST("getTransaction_list.php")
    Call<TransactionListWrapper> getTranscationLs(@Field("staff_id") String stateid, @Field("datesearch") String datesearch);

    @POST("getstatelist.php")
    Call<StateWrapper> getState();

    @POST("getstafflist.php")
    Call<StaffListWrapper> getstaffList();

    @POST("getaccount_typelist.php")
    Call<AccountTypeWrapper> getAcounttypeLs();


    @POST("getuserlist.php")
    Call<UsersWrapper> getUsers();

    @POST("getstore_list.php")
    Call<StoreWrapper> getStore();


    @Multipart
    @POST("upload_customer_img.php")
    Call<UploadImageWrapper> updateProfileRetrofit(@Part MultipartBody.Part item_image);


    @FormUrlEncoded
    @POST("cust_regiter.php")
    Call<InfoWrapper> custRegistrations(
            @Field("auth_id") String auth_id,
            @Field("acc_no") String acc_no,
            @Field("names") String names,
            @Field("marital_status") String marital_status,
            @Field("gender") String gender,
            @Field("dob") String dob,
            @Field("lga") String lga,
            @Field("c_address") String c_address,
            @Field("city") String city,
            @Field("reg_state") String reg_state,
            @Field("c_state") String c_state,
            @Field("mobile") String mobile,
            @Field("email") String email,
            @Field("acc_type") String acc_type,
            @Field("pay_mode") String pay_mode,
            @Field("amount") String amount,
            @Field("reg_fee") String reg_fee,
            @Field("image") String image,
            @Field("staff_id") String staff_id,
            @Field("acc_officer_id") String acc_officer_id,
            @Field("branch_id") String branch_id );
}