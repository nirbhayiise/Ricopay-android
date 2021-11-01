package com.np.ricopayapp.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.loader.content.CursorLoader;

import com.bumptech.glide.Glide;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.np.ricopayapp.R;
import com.np.ricopayapp.interfaces.CustomerRegisterCallback;
import com.np.ricopayapp.interfaces.LGASpinnerData_CallBack;
import com.np.ricopayapp.interfaces.SpinnerData_CallBack;
import com.np.ricopayapp.interfaces.StoreListCallback;
import com.np.ricopayapp.interfaces.UploadImageCallBack;
import com.np.ricopayapp.interfaces.UserListCallback;
import com.np.ricopayapp.responsemodels.state_list.Datum;
import com.np.ricopayapp.responsemodels.upload_image.UploadImageWrapper;
import com.np.ricopayapp.respositrys.CustomerRegisterRepositry;
import com.np.ricopayapp.respositrys.LGAListingRepositry;
import com.np.ricopayapp.respositrys.StateListingRepositry;
import com.np.ricopayapp.respositrys.StoreListingRepositry;
import com.np.ricopayapp.respositrys.UploadImageRepositry;
import com.np.ricopayapp.respositrys.UserListingRepositry;
import com.np.ricopayapp.services.APIs;
import com.np.ricopayapp.services.Const;
import com.np.ricopayapp.services.SingletonObjectAccess;
import com.np.ricopayapp.utils.AppData;
import com.np.ricopayapp.utils.UserPreference;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import java.util.Calendar;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class FragmentCustomerRegistration extends Fragment implements SpinnerData_CallBack, LGASpinnerData_CallBack, StoreListCallback, UserListCallback, UploadImageCallBack, CustomerRegisterCallback {
    private int mYear, mMonth, mDay, mHour, mMinute;
    EditText accountno,
            names,
            dob,
            mobile,
            mailid,

    address,
            city;

    CustomerRegisterCallback cr_callback;
    String slectstate, selectlga, selectoriginstate, selectaccountoffice, selectbranch;
    RadioButton male, female, other, single, married;
    ImageView imgpro;
    TextView headername, signbtn;
    List<String> statename;
    List<String> stateId;
    List<String> lgaName;
    List<String> lgaId;
    List<String> storeName;
    List<String> storeId;

    List<String> userName;
    List<String> userId;

    List<String> paymentmodeList;
    List<String> paymentmodelistId;

    private Uri selectedImage;
    String marriedStatus = "";
    String genderStr = "";
    String selectedpaymode = "";
    LGASpinnerData_CallBack lgscallback;
    MaterialSpinner staetlist, stateorigin, lga, accountoffice, branch, paymode;
    String currentPhotoPath;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.customer_regi_activity, container, false);
        cr_callback = this;
        accountno = view.findViewById(R.id.accountno);
        single = view.findViewById(R.id.single);
        married = view.findViewById(R.id.married);

        male = view.findViewById(R.id.male);
        female = view.findViewById(R.id.female);
        other = view.findViewById(R.id.other);

        names = view.findViewById(R.id.names);
        dob = view.findViewById(R.id.dob);
        mobile = view.findViewById(R.id.mobile);
        mailid = view.findViewById(R.id.mailid);

        address = view.findViewById(R.id.address);
        city = view.findViewById(R.id.city);
        signbtn = view.findViewById(R.id.signbtn);

        accountno.setText(SingletonObjectAccess.getRandomNumberString());
        lgscallback = this;
        statename = new ArrayList<String>();
        stateId = new ArrayList<String>();
        lgaName = new ArrayList<String>();
        lgaId = new ArrayList<String>();
        storeName = new ArrayList<String>();
        storeId = new ArrayList<String>();
        userName = new ArrayList<String>();
        userId = new ArrayList<String>();

        paymentmodeList = new ArrayList<String>();
        paymentmodelistId = new ArrayList<String>();
        paymentmodeList.add("Daily");
        paymentmodeList.add("Weekly");
        paymentmodeList.add("Monthly");
        paymentmodelistId.add("1");
        paymentmodelistId.add("21");
        paymentmodelistId.add("3");

        headername = getActivity().findViewById(R.id.loanform);
        headername.setText("Customer Registration");
        staetlist = view.findViewById(R.id.statelist);
        stateorigin = view.findViewById(R.id.stateorigin);
        accountoffice = view.findViewById(R.id.accountoffice);
        paymode = view.findViewById(R.id.paymode);
        branch = view.findViewById(R.id.branch);
        lga = view.findViewById(R.id.lga);
        imgpro = view.findViewById(R.id.imgpro);
        StateListingRepositry.getInstance().getState(getActivity(), this);

        StoreListingRepositry.getInstance().getstoreList(getActivity(), this);
        UserListingRepositry.getInstance().getUserList(getActivity(), this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, paymentmodeList);
        // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        paymode.setAdapter(adapter);

        single.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marriedStatus = "Single";
            }
        });
        married.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marriedStatus = "Married";
            }
        });
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genderStr = "Male";
            }
        });
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genderStr = "Female";
            }
        });
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genderStr = "Other";
            }
        });
        staetlist.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

                lgaName.clear();
                lgaId.clear();
                slectstate = stateId.get(position);
                LGAListingRepositry.getInstance().getLGAData(stateId.get(position), getActivity(), lgscallback);
            }
        });
        paymode.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                selectedpaymode = paymentmodelistId.get(position);
            }
        });
        stateorigin.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                selectoriginstate = stateId.get(position);
            }
        });
        lga.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                selectlga = lgaId.get(position);
            }
        });
        accountoffice.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                selectaccountoffice = userId.get(position);
            }
        });
        branch.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                selectbranch = storeId.get(position);
            }
        });
        imgpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Add Photo!");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options[item].equals("Take Photo")) {
                            try {
//                                Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                                startActivityForResult(takePicture, 0);
                                dispatchTakePictureIntent();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } else if (options[item].equals("Choose from Gallery")) {
                            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            someActivityResultLauncher.launch(intent);
                        } else if (options[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();


//                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                //startActivityForResult(i, 100);
//                someActivityResultLauncher.launch(i);

            }
        });
        signbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CustomerRegisterRepositry.getInstance().customerRegsiterInvok(UserPreference.getUid(getActivity()), accountno.getText().toString(), names.getText().toString(), marriedStatus, genderStr, dob.getText().toString(), selectlga, address.getText().toString(), city.getText().toString(), selectoriginstate, slectstate, mobile.getText().toString(), mailid.getText().toString(), selectaccountoffice, selectedpaymode, "", "", AppData.getAppDataInstance(getActivity()).getFilename(), UserPreference.getUid(getActivity()), selectaccountoffice, selectbranch, getActivity(), cr_callback);
            }
        });
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                dob.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        return view;
    }

    // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        selectedImage = data.getData();
                        Toast.makeText(getActivity(), ""+selectedImage, Toast.LENGTH_SHORT).show();

                        uploadFile(selectedImage, "1");

                    }
                }
            });

    @Override
    public void onStateList(List<Datum> data) {

        for (int index = 0; index < data.size(); index++) {
            statename.add(data.get(index).getStatename());
            stateId.add(data.get(index).getSid());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, statename);
        // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        staetlist.setAdapter(adapter);
        stateorigin.setAdapter(adapter);
    }

    @Override
    public void onLGAList(List<com.np.ricopayapp.responsemodels.lga_list.Datum> data) {


        for (int index = 0; index < data.size(); index++) {
            lgaName.add(data.get(index).getLganame());
            lgaId.add(data.get(index).getLid());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, lgaName);
        // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        lga.setAdapter(adapter);
    }

    @Override
    public void onStoreSuccess(List<com.np.ricopayapp.responsemodels.store_response.Datum> data) {


        for (int index = 0; index < data.size(); index++) {
            storeName.add(data.get(index).getStoreName());
            storeId.add(data.get(index).getId());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, storeName);
        // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        branch.setAdapter(adapter);

    }

    @Override
    public void onStoreFailed(String msg) {

    }

    @Override
    public void onUserListSuccess(List<com.np.ricopayapp.responsemodels.users_list.Datum> data) {


        for (int index = 0; index < data.size(); index++) {
            userName.add(data.get(index).getNames());
            userId.add(data.get(index).getId());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, userName);
        // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        accountoffice.setAdapter(adapter);
    }

    @Override
    public void onUserListFailed(String msg) {

    }

    private void uploadFile(Uri fileUri, String uid) {


        //creating a file
        File file = new File(getRealPathFromURI(fileUri));

        //creating request body for file
        RequestBody requestFile = RequestBody.create(MediaType.parse(getActivity().getContentResolver().getType(fileUri)), file);

        MultipartBody.Part body = MultipartBody.Part.createFormData("item_image", file.getName(), requestFile);

        UploadImageRepositry.getInstance().UploadImage(body, getActivity(), this);
    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getActivity(), contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

    @Override
    public void onUploadImageSuccess(String filename) {

        AppData.getAppDataInstance(getActivity()).setFilename(filename);
        Toast.makeText(getActivity(), "" + filename, Toast.LENGTH_SHORT).show();
        Glide.with(getActivity()).load(Const.BASE_URL_IMG + filename).into(imgpro);
    }

    @Override
    public void onUploadImageFailed(String msg) {
        Toast.makeText(getActivity(), "" + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRegCustomerRegisterSuccessful(String data) {
        SingletonObjectAccess.DialogMessageCustomerRegsit(new Dialog(getActivity()), getActivity(), "Customer Registration successful!");
        Toast.makeText(getActivity(), "Customer Registration successful!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCustomerRegisterUserFailed(String msg) {
        Toast.makeText(getActivity(), "" + msg, Toast.LENGTH_SHORT).show();
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
//        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getActivity(),
                        "net.smallacademy.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                //startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
                someActivityResultLauncher.launch(takePictureIntent);
            }
        }
    }
}