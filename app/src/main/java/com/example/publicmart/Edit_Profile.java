package com.example.publicmart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Edit_Profile extends BaseActivity {

    LinearLayout submit;
    JSONObject jsonString;
    String request,code,message;
    EditText Fname,Mname,Lname,contact_no,email_id,h_no,tehsil,village,dist,pinode;
    Integer StateCode, StateKey;
    Spinner state;
    ArrayAdapter<SpinnerModel> spinner_adapter;
    ArrayList<SpinnerModel> names ;
    String StateName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_edit__profile, contentFrameLayout);

        submit = (LinearLayout) findViewById(R.id.submit_pro);
        Fname=(EditText)findViewById(R.id.fname_edit);
        Mname=(EditText)findViewById(R.id.mname_edit);
        Lname=(EditText)findViewById(R.id.lname_edit);
        contact_no=(EditText)findViewById(R.id.contact_numbered);
        email_id=(EditText)findViewById(R.id.email_ed);
        h_no=(EditText)findViewById(R.id.H_no_edit);
        tehsil=(EditText)findViewById(R.id.tehsil_edit);
        village=(EditText)findViewById(R.id.village_edit);
        dist=(EditText)findViewById(R.id.district_edit);
        pinode=(EditText)findViewById(R.id.pin_code_edit);
        state=(Spinner)findViewById(R.id.state_sp);

        Ed_profile_view();

        statecode();
        names = new ArrayList<SpinnerModel>();



        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                // Toast.makeText(getApplication(), ""+names.get(position).getStateCode(),Toast.LENGTH_LONG).show();
                StateKey = names.get(position).getStateCode();
                Log.e("codeeeeestaeeeeeee","test   "+ StateKey);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });








        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    SharedPreferences sp = getSharedPreferences("UserLog", 0);
                    String CustKey = sp.getString("UserKey", null);

                    JSONObject values = new JSONObject();
                    values.put("CustKey",Integer.parseInt(CustKey));
                    values.put("FName", Fname.getText().toString());
                    values.put("MName", Mname.getText().toString());
                    values.put("LName", Lname.getText().toString());
                    values.put("HouseNo", h_no.getText().toString());
                    values.put("Tehsil", tehsil.getText().toString());
                    values.put("Village", village.getText().toString());
                    values.put("District", dist.getText().toString());
                    values.put("StateKey", StateKey);
                    values.put("PinNo", pinode.getText().toString());
                    values.put("MobileNo", contact_no.getText().toString());
                    values.put("Email", email_id.getText().toString());
                    values.put("Status", true);
                    values.put("CreatedBy", Integer.parseInt(CustKey));
                    //                    values.put("ReturnValue", 0);
                    jsonString = new JSONObject();
                    jsonString.put("Token", "0001");
                    jsonString.put("call", "UpdateCustomerProfile");
                    jsonString.put("values", values);
                    request = jsonString.toString();

                } catch (
                        JSONException e) {
                    e.printStackTrace();
                }

                editprofile(request);
            }
        });
    }

        private void editprofile(final String request) {


            Log.e("gettttt","in"+request);



            String URL = this.getString(R.string.Url)+"Save";


            StringRequest stringRequest=new StringRequest(Request.Method.POST, URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Log.e("Jsonnnn",""+response);
                            // p1.dismiss();

                            try {
                                JSONObject o = new JSONObject(response);
                                String data = response;
                                Object json = new JSONTokener(data).nextValue();
                                if (json instanceof JSONObject) {
                                    Log.e("objectttttt", "" + json);
                                }
                                //you have an object
                                else if (json instanceof JSONArray) {
                                    Log.e("Arrayyyyyyy", "" + json);
                                }


                                Log.e("tryyyyyyyyy", "in" + o);


                                code = o.getString("responseCode");
                                message = o.getString("responseMessage");

                                Log.e("resppppppp", "" + code);

//                                if (code.equalsIgnoreCase("-100"))
//                                {
//
//                                    Log.e("resppppppp","ifffff"+code);


//                            }
                                     Toast.makeText(Edit_Profile.this,message,Toast.LENGTH_LONG).show();
                                    Intent intent =new Intent(Edit_Profile.this,Profile.class);
                                    startActivity(intent);



//                                else {
//                                    Toast.makeText(Edit_Profile.this,message,Toast.LENGTH_LONG).show();
//                                }



                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "No Response From Server ", Toast.LENGTH_LONG).show();

                        }
                    }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> param = new HashMap<String, String>();
                    param.put("jsonString",request );
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
    public void Ed_profile_view()
    {
        SharedPreferences sp = getSharedPreferences("UserLog",0);
        String CustKey =  sp.getString("UserKey",null);

        try {
            JSONObject values = new JSONObject();
            values.put("CustKey",CustKey);

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
                                    contact_no.setText(jsonObject.getString("MobileNo"));
                                    email_id.setText(jsonObject.getString("Email"));
                                    h_no.setText(jsonObject.getString("HouseNo"));
                                    tehsil.setText(jsonObject.getString("Tehsil"));
                                    village.setText(jsonObject.getString("Village"));
                                    dist.setText(jsonObject.getString("District"));
                                    pinode.setText(jsonObject.getString("PinNo"));
                                }
                            }
                            else {
                                Toast.makeText(Edit_Profile.this,message,Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "No Response From Server ", Toast.LENGTH_LONG).show();

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

    private void statecode() {


        try {
            JSONObject values = new JSONObject();

            jsonString = new JSONObject();
            jsonString.put("Token", "0001");
            jsonString.put("call", "GetActiveStates");
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

                                    StateName=jsonObject.getString("StateName");
                                    StateCode=jsonObject.getInt("StateKey");
                                    SpinnerModel items  =new SpinnerModel(StateCode, StateName);
                                    names.add(items);
                                    Log.e("from jsonnnn", "  " + jsonObject.getString("StateName"));
                                    Log.e("namessssss", "  " + names);


                                }


                                spinner_adapter = new ArrayAdapter<SpinnerModel>(Edit_Profile.this,android.R.layout.simple_spinner_dropdown_item,names);
                                spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                state.setAdapter(spinner_adapter);





                            }


                            else {
                                Toast.makeText(Edit_Profile.this,message,Toast.LENGTH_LONG).show();
                            }




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "No Response From Server ", Toast.LENGTH_LONG).show();

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

        // Volley.getInstance(this).addToRequestQueue(stringRequest);
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


        Log.e("statecodeeeeeee",""+ StateKey);



    }




    }

