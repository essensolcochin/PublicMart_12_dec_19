package com.essensol.publicmart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.HashMap;
import java.util.Map;

public class Profile extends BaseActivity {

    TextView  Fname,Mname,Lname,contact_no,email_id,h_no,tehsil,village,dist,pinode,membership,proicon,proid,state_name;
    JSONObject jsonString;
    String request,code,message;
    TextView edit,save;
    EditText contact,email,name,address,accHname,accNo,ifscc,Bname,Branch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_profile, contentFrameLayout);

        proid=(TextView)findViewById(R.id.profileid);
        Fname=(TextView)findViewById(R.id.fname);

        contact=(EditText) findViewById(R.id.contact);
        email=(EditText) findViewById(R.id.email);
        name=(EditText) findViewById(R.id.name);
        address=(EditText) findViewById(R.id.address);
        accHname=(EditText) findViewById(R.id.AcchName);
        accNo=(EditText) findViewById(R.id.AccNo);
        ifscc=(EditText) findViewById(R.id.ifsc);
        Bname=(EditText) findViewById(R.id.BankName);
        Branch=(EditText) findViewById(R.id.Branch);

        proicon=(TextView)findViewById(R.id.proicon);

        edit=findViewById(R.id.edit);



//        statecode();
//        names = new ArrayList<SpinnerModel>();
        profile_view();




        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(Profile.this,Edit_Profile.class);
                startActivity(intent);
            }
        });



    }

    public void profile_view()
    {
        SharedPreferences sp = getSharedPreferences("UserLog",0);
        String CustKey =  sp.getString("UserKey",null);

        try {
            JSONObject values = new JSONObject();
            values.put("CustKey",Integer.parseInt(CustKey));

            jsonString = new JSONObject();
            jsonString.put("Token", "0001");
            jsonString.put("call", "GetCustDetailsById");
            jsonString.put("values", values);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Log.e("gettttt","in"+request);



        String URL = this.getString(R.string.Url)+"Select";


        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("Jsonnnn",""+response);
                        // p1.dismiss();

                        try {


                            JSONObject o     = new JSONObject(response);


                            String data = response;
                            Object json = new JSONTokener(data).nextValue();
                            if (json instanceof JSONObject){
                                Log.e("objectttttt",""+json);
                            }
                            //you have an object
                            else if (json instanceof JSONArray){
                                Log.e("Arrayyyyyyy",""+json);
                            }


                            Log.e("tryyyyyyyyy","in"+o);

                            ;
                            code = o.getString("responseCode");
                            message=o.getString("responseMessage");

                            Log.e("resppppppp",""+code);


                            if (code.equalsIgnoreCase("0"))
                            {


                                Log.e("resppppppp","ifffff"+code);

                                JSONArray json_array2 = o.getJSONArray("result");


                                JSONObject jsonObject;


                                int j;
                                for (j = 0; j < json_array2.length(); j++) {
                                    jsonObject = json_array2.getJSONObject(j);

                                   Fname.setText(jsonObject.getString("FName"));
                                   Mname.setText(jsonObject.getString("MName"));
                                   Lname.setText(jsonObject.getString("LName"));
                                    proid.setText(jsonObject.getString("CustCode"));
                                    proicon.setText(jsonObject.getString("FName").substring(0,1));
                                    membership.setText(jsonObject.getString("MSTypeKey"));
                                   contact_no.setText(jsonObject.getString("MobileNo"));
                                    email_id.setText(jsonObject.getString("Email"));
                                    h_no.setText(jsonObject.getString("HouseNo"));
                                    tehsil.setText(jsonObject.getString("Tehsil"));
                                    village.setText(jsonObject.getString("Village"));
                                    dist.setText(jsonObject.getString("District"));
                                    pinode.setText(jsonObject.getString("PinNo"));
                                    state_name.setText(jsonObject.getString("StateName"));
                                }
                            }
                            else {
                                Toast.makeText(Profile.this,message,Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Some Error Occurred ", Toast.LENGTH_SHORT).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("jsonString",jsonString.toString() );
                Log.e("paramssss",""+param);
                return param;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> param = new HashMap<String, String>();
                param.put("Content-Type","application/x-www-form-urlencoded");
                return param;
            }
        }
                ;
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }







    /////EDIT PROFILE/////






}
