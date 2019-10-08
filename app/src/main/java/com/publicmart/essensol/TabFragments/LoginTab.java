package com.publicmart.essensol.TabFragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.publicmart.essensol.Activities.Home;
import com.publicmart.essensol.Activities.Registration;
import com.publicmart.essensol.ModelClasses.RealmShopModel;
import com.publicmart.essensol.R;
import com.publicmart.essensol.RetrofitUtils.ApiClient;
import com.publicmart.essensol.RetrofitUtils.ApiInterface;
import com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses.LoginResponse;
import com.publicmart.essensol.Utility;

import java.util.List;
import java.util.Objects;
import java.util.Random;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginTab extends Fragment {

    TextView reg;
    LinearLayout log;
    EditText username, password;
    String code, message, request, token;
    SharedPreferences sp;
    Realm realm;
    private ProgressDialog progressdialog;
    private static final Random random = new Random();
    private static final String CHARS = "abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ234567890!@#$";
    ProgressDialog progress;
    int j;
    ApiInterface apiInterface;


    public LoginTab() {
     }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.login_tab, container, false);

        reg = (TextView) RootView.findViewById(R.id.register);

        username = (EditText) RootView.findViewById(R.id.username);
        password = (EditText) RootView.findViewById(R.id.password);
        log = RootView.findViewById(R.id.login);

        realm = Realm.getDefaultInstance();



        progressdialog = new ProgressDialog(getActivity());
        progressdialog.setTitle("Publicmart");
        progressdialog.setMessage("Gathering Information");
        apiInterface = ApiClient.getClient().create(ApiInterface.class);


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Registration.class);
                startActivity(intent);

            }
        });



        log.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if (username.getText().toString().equalsIgnoreCase("")) {
                    username.setError("Field Empty");
                } else if (password.getText().toString().equalsIgnoreCase("")) {
                    password.setError("Field Empty");
                } else {
                    if(Utility.isNetworkConnectionAvailable(Objects.requireNonNull(getActivity()))) {
                        loginUser();
                    }
                    else {
                        progressdialog.cancel();
                        Utility.ShowCustomToast(" No Network Connection Available Check Your Internet Settings",getActivity());
                    }

                }

            }
        });

        return RootView;


    }

    private void loginUser(){

        String Uname= username.getText().toString();
        String Password= password.getText().toString();

        SharedPreferences SaveToken =   getActivity().getSharedPreferences("GetToken",MODE_PRIVATE);
        String token=SaveToken.getString("Token",null);





        apiInterface.login(Uname,Password,token,"M").enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {
                progressdialog.cancel();

                if (response.isSuccessful() && response.code() == 200) {
                    assert response.body() != null;
                    if (response.body().getCode().equalsIgnoreCase("0")) {

                        //selecting Inner Json Array From response
                        final List<LoginResponse.LoginJsonArray> responseBody = response.body().getLoginresponse();

                        for (j = 0; j < responseBody.size(); j++) {

                            switch (responseBody.get(j).getLoginResult()){

                                case "1":

                                    String Count = responseBody.get(j).getCartCount();

                                    if(!Count.equalsIgnoreCase("0"))
                                    {
                                        realm.beginTransaction();
                                        RealmShopModel addToCart1 = new RealmShopModel();
                                        addToCart1.setCount(Count);
                                        realm.insertOrUpdate(addToCart1);
                                        realm.commitTransaction();
                                    }

                                    sp = getActivity().getSharedPreferences("UserLog",MODE_PRIVATE);
                                    SharedPreferences.Editor editor =sp.edit();

                                    editor.putString("UserKey",responseBody.get(j).getUserKey());
                                    editor.putString("CustKey",responseBody.get(j).getCustKey());
                                    editor.putString("Username",responseBody.get(j).getUserName());
                                    editor.putString("CustomerName",responseBody.get(j).getCustomerName());
                                    editor.putString("CustCode",responseBody.get(j).getCustCode());
                                    editor.putString("MemberShip",responseBody.get(j).getMemberShip());
                                    editor.putString("amnt",responseBody.get(j).getAmount());
                                    editor.putString("MobileNo",responseBody.get(j).getMobileNo());
                                    editor.putString("Email",responseBody.get(j).getEmail());


                                    editor.apply();
                                    Log.e("Log Bool","  "+sp.getBoolean("LoggedUser",false));
                                    Log.e("Log keyyyy","  "+sp.getString("UserKey",null));

                                    Log.e("getPaidStatus","  "+responseBody.get(j).getPaidStatus());

                                if(responseBody.get(j).getPaidStatus().equalsIgnoreCase("True")) {

                                    if (responseBody.get(j).getProfile().equalsIgnoreCase("True")) {
                                        SharedPreferences sp = getActivity().getSharedPreferences("UserLog", MODE_PRIVATE);
                                        SharedPreferences.Editor edit = sp.edit();
                                        edit.putBoolean("LoggedUser", true);
                                        edit.apply();

                                        Intent intent = new Intent(getActivity(), Home.class);
                                        startActivity(intent);
                                        getActivity().finish();

                                    } else {
                                        SharedPreferences sp = getActivity().getSharedPreferences("UserLog", MODE_PRIVATE);
                                        SharedPreferences.Editor edit = sp.edit();
                                        edit.putBoolean("LoggedUser", true);
                                        edit.apply();

                                        //ToDo change Home to Edit Profile activity in this intent
                                        Intent intent = new Intent(getActivity(), Home.class);
                                        startActivity(intent);
                                        getActivity().finish();
                                    }

                                }
                                else {

                                    Utility.ShowCustomToast("Payment Not Completed",getActivity());
                                }
                                break;

                                case "2":

                                  Utility.ShowCustomToast(responseBody.get(j).getLoginMsg(),getActivity());
                                    break;

                                case "3":

                                  Utility.ShowCustomToast(responseBody.get(j).getLoginMsg(),getActivity());
                                    break;


                            }


//                                }
//
//                                else{
//
//                                    Utility.ShowCustomToast("Your Account has been disabled contact our team to redeem your account",MainActivity.this);
//
////                                    Intent intent =new Intent(MainActivity.this,Payment.class);
////
////                                    startActivity(intent);
////                                    finish();
//                                }




                        }
                    }
                }
                else if(response.code() == 401) {
                    Utility.ShowCustomToast("Authentication Failed ",getActivity());
                    Log.e("Error  Codeeeeeeeeeeee","  "+response.code());
                }

                else if( response.code() == 500) {
                    Utility.ShowCustomToast("A Server Error has been Occurred",getActivity());
                    Log.e("Error  Codeeeeeeeeeeee","  "+response.code());
                }

                else if(response.code() == 408) {
                    Utility.ShowCustomToast("A Network Error has been Occurred Check your Connectivity Settings",getActivity());
                    Log.e("Error  Codeeeeeeeeeeee","  "+response.code());
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                Log.e("Error On Failure","Failedddddd");
                Utility.ShowCustomToast("A Network Error has been Occurred Check your Connectivity Settings",getActivity());
            }
        });




    }



}

