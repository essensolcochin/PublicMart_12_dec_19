package com.publicmart.android.Activities;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.publicmart.android.MyApplication;
import com.publicmart.android.R;
import com.publicmart.android.ModelClasses.RealmShopModel;
import com.publicmart.android.RetrofitUtils.ApiClient;
import com.publicmart.android.RetrofitUtils.ApiInterface;
import com.publicmart.android.RetrofitUtils.RetrofitResponseClasses.LoginResponse;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.publicmart.android.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;


public class MainActivity extends AppCompatActivity {
    JSONObject jsonString;
    TextView reg;
    LinearLayout log;
    EditText username,password;
    String code,message,request,token;
    SharedPreferences sp;
    Realm realm;
    private ProgressDialog progressdialog;
    private static final Random random = new Random();
    private static final String CHARS = "abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ234567890!@#$";
    ProgressDialog progress;
    int j;
    ApiInterface apiInterface;
//    ConnectivityManagerClass reciever =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reg = (TextView) findViewById(R.id.register);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        realm = Realm.getDefaultInstance();


        progressdialog = new ProgressDialog(MainActivity.this);

//





//        Utility.ShowCustomToast("Test",MainActivity.this);

        progress = new ProgressDialog(MainActivity.this);
        progress.setTitle("Publicmart");
        progress.setMessage("Gathering Information");



//        reciever =new ConnectivityManagerClass();
//
//        registerReceiver(reciever, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));


//        if (Utility.isNetworkConnectionAvailable(MainActivity.this)) {
//
//
//            progress.show();
////            getFirebaseToken();
//        }
//        else{
//            progress.cancel();
//            Utility.ShowCustomToast(" No Network Connection Available Check Your Internet Settings ",MainActivity.this);
//
////            changeNetworkStatus(false);
//
//        }


        apiInterface= ApiClient.getClient().create(ApiInterface.class);



        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,Registration.class);
                startActivity(intent);

            }
        });
        log = findViewById(R.id.login);



        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equalsIgnoreCase("")){
                    username.setError("Field Empty");
                }

                else if(password.getText().toString().equalsIgnoreCase(""))
                {
                    password.setError("Field Empty");
                }
                   else{





//                    if(token==null)
//                    {
//                       FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(MainActivity.this, new OnSuccessListener<InstanceIdResult>() {
//                        @Override
//                        public void onSuccess(InstanceIdResult instanceIdResult) {
//                            String newToken = instanceIdResult.getToken();
//                            Log.e("newTokennnnnnnn  ", newToken);
//                            token = newToken;
//
//
//                            SharedPreferences SaveToken =   getSharedPreferences("GetToken",MODE_PRIVATE);
//                            SharedPreferences.Editor editor =SaveToken.edit();
//                            editor.putString("Token",token);
//                            editor.apply();
//                        }
//                    });
//
//                    }

                    progressdialog.setTitle("Publicmart");
                    progressdialog.setMessage("Gathering Information");
                    progressdialog.show();


                    try {

                        JSONObject values = new JSONObject();
                        values.put("UserName", username.getText().toString());
                        values.put("UserPwd", password.getText().toString());
                        values.put("AppToken", token);
                        values.put("Mode", "M");

                        jsonString = new JSONObject();
                        jsonString.put("Token", "0001");
                        jsonString.put("call", "CheckLogin");
                        jsonString.put("values", values);
                        request = jsonString.toString();


                    } catch (
                            JSONException e) {
                        e.printStackTrace();
                    }

                    if(Utility.isNetworkConnectionAvailable(MainActivity.this)) {
                        loginUser();
                    }
                    else {
                        progressdialog.cancel();
                        Utility.ShowCustomToast(" No Network Connection Available Check Your Internet Settings",MainActivity.this);
                    }




                }





            }

        });


    }
    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }






//    public static String getToken(int length) {
//        StringBuilder token = new StringBuilder(length);
//        for (int i = 0; i < length; i++) {
//            token.append(CHARS.charAt(random.nextInt(CHARS.length())));
//        }
////        Log.e("Mytokennnnn",""+token.toString());
//        return token.toString();
//
//    }

                @Override
                public void onBackPressed() {
                    moveTaskToBack(true);
                }




    private void loginUser(){

        String Uname= username.getText().toString();
        String Password= password.getText().toString();

        SharedPreferences SaveToken =   getSharedPreferences("GetToken",MODE_PRIVATE);
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

                                String Count = responseBody.get(j).getCartCount();

                                if(!Count.equalsIgnoreCase("0"))
                                {
                                    realm.beginTransaction();
                                    RealmShopModel addToCart1 = new RealmShopModel();
                                    addToCart1.setCount(Count);
                                    realm.insertOrUpdate(addToCart1);
                                    realm.commitTransaction();
                                }

                                sp = getSharedPreferences("UserLog",MODE_PRIVATE);
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

                                if(responseBody.get(j).getPaidStatus().equalsIgnoreCase("True"))

                                {

                                    if(responseBody.get(j).getProfile().equalsIgnoreCase("True"))
                                    {
                                        SharedPreferences sp = getSharedPreferences("UserLog",MODE_PRIVATE);
                                        SharedPreferences.Editor edit = sp.edit();
                                        edit.putBoolean("LoggedUser",true);
                                        edit.apply();
                                        Intent intent =new Intent(MainActivity.this,Home.class);
                                        startActivity(intent);
                                        finish();

                                    }
                                    else {
                                        Intent intent =new Intent(MainActivity.this,Profile.class);
                                        startActivity(intent);
                                        finish();
                                    }


                                }

                                else{

                                    Intent intent =new Intent(MainActivity.this,Payment.class);

                                    startActivity(intent);
                                    finish();
                                }




                            }
                        }
                    }
                    else if(response.code() == 401) {
                        Utility.ShowCustomToast("Authentication Failed ",MainActivity.this);
                        Log.e("Error  Codeeeeeeeeeeee","  "+response.code());
                    }

                    else if( response.code() == 500) {
                        Utility.ShowCustomToast("A Server Error has been Occurred",MainActivity.this);
                        Log.e("Error  Codeeeeeeeeeeee","  "+response.code());
                    }

                    else if(response.code() == 408) {
                        Utility.ShowCustomToast("A Network Error has been Occurred Check your Connectivity Settings",MainActivity.this);
                        Log.e("Error  Codeeeeeeeeeeee","  "+response.code());
                    }

                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {

                    Log.e("Error On Failure","Failedddddd");
                    Utility.ShowCustomToast("A Network Error has been Occurred Check your Connectivity Settings",MainActivity.this);
                }
            });




}

    @Override
    protected void onPause() {

        super.onPause();
        MyApplication.activityPaused();// On Pause notify the Application
    }

    @Override
    protected void onResume() {

        super.onResume();
        MyApplication.activityResumed();// On Resume notify the Application
    }


//    public   void  getFirebaseToken(){
//        Log.e("InsideChangenetwork ", "Connected");
//        progress.show();
//        Runnable progressRunnable = new Runnable() {
//
//            @Override
//            public void run() {
//
//                FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(MainActivity.this, new OnSuccessListener<InstanceIdResult>() {
//                    @Override
//                    public void onSuccess(InstanceIdResult instanceIdResult) {
//                        String newToken = instanceIdResult.getToken();
//                        Log.e("newTokennnnnnnn  ", newToken);
//                        token = newToken;
//
//
//                        SharedPreferences SaveToken =   getSharedPreferences("GetToken",MODE_PRIVATE);
//                        SharedPreferences.Editor editor =SaveToken.edit();
//                        editor.putString("Token",token);
//                        editor.apply();
//                    }
//                });
//
//                progress.cancel();
//            }
//        };
//
//        Handler pdCanceller = new Handler();
//        pdCanceller.postDelayed(progressRunnable, 500);
//
//    }
}

