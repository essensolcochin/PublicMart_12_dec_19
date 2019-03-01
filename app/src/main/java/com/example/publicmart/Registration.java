package com.example.publicmart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;

public class Registration extends AppCompatActivity {
    LinearLayout regist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        regist = (LinearLayout)findViewById(R.id.submit);

        fname = (EditText)findViewById(R.id.fname);
        mname = (EditText)findViewById(R.id.Mname);
        lname = (EditText)findViewById(R.id.lname);


        Day = (Spinner) findViewById(R.id.day);
        Month = (Spinner) findViewById(R.id.Bmonth);
        Year = (Spinner) findViewById(R.id.Byear);

        house_no = (EditText)findViewById(R.id.Hno);
        tehsil = (EditText)findViewById(R.id.tehsil);
        village = (EditText)findViewById(R.id.village);
        district = (EditText)findViewById(R.id.district);
        pincode = (EditText)findViewById(R.id.pin);
        contact_no = (EditText)findViewById(R.id.contactno);
        email = (EditText)findViewById(R.id.email);
        sponsership_id = (EditText)findViewById(R.id.sponcerid);
        nominee = (EditText)findViewById(R.id.nominee);
        relationship = (EditText)findViewById(R.id.Relation);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        cnfmpassword = (EditText)findViewById(R.id.Confirmpasswd);
        state = (Spinner)findViewById(R.id.state);




        getCodes();
        names = new ArrayList<SpinnerModel>();

        Log.e("getcodeeeeee","test   "+names.size());








       ArrayAdapter<String> spinner_adapterDay = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array_day);
        spinner_adapterDay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Day.setAdapter(spinner_adapterDay);

         ArrayAdapter<String> spinner_adapterMonth = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array_Mnth);
        spinner_adapterMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Month.setAdapter(spinner_adapterMonth);

         ArrayAdapter<String> spinner_adapterYear = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array_BYear);
        spinner_adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Year.setAdapter(spinner_adapterYear);



        Day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Bday = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Bmonth = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Byear = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        ArrayAdapter<SpinnerModel> spinner_adapter = new ArrayAdapter<SpinnerModel>(this,
//                android.R.layout.simple_spinner_item, names);
//        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        state.setAdapter(spinner_adapter);




        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {

                    JSONObject values = new JSONObject();
                    values.put("CustKey", 0);
                    values.put("FName", fname.getText().toString());
                    values.put("MName", mname.getText().toString());
                    values.put("LName", lname.getText().toString());
                    values.put("DOB", Bday+"-"+Bmonth+"-"+Byear);
                    values.put("HouseNo", house_no.getText().toString());
                    values.put("Tehsil", tehsil.getText().toString());
                    values.put("Village", village.getText().toString());
                    values.put("District", district.getText().toString());
                    values.put("StateKey", 1);
                    values.put("PinNo", pincode.getText().toString());
                    values.put("MobileNo", contact_no.getText().toString());
                    values.put("AlterMobileNo","");
                    values.put("Email", email.getText().toString());
                    values.put("BankKey", 0);
                    values.put("BranchName", "");
                    values.put("AccountNo", "");
                    values.put("IFSCCode", "");
                    values.put("PanNo", "");
                    values.put("Nominee", nominee.getText().toString());
                    values.put("Relationship", relationship.getText().toString());
                    values.put("SponsorId", sponsership_id.getText());
                    values.put("MSTypeKey", 4);
                    values.put("Status", true);
                    values.put("CreatedBy", 0);
//                    values.put("ReturnValue", 0);
                    values.put("UserName", username.getText().toString());
                    values.put("UserPwd", password.getText().toString());
                    Log.e("testtttttt","in"+password.getText().toString());
                    jsonString = new JSONObject();
                    jsonString.put("Token", "0001");
                    jsonString.put("call", "SaveUpdateCustomerMaster");
                    jsonString.put("values", values);
                    request = jsonString.toString();

                } catch (
                        JSONException e) {
                    e.printStackTrace();
                }





                Register(request);



            }
        });
    }




    private void Register(final String request) {


        Log.e("gettttt","in"+request);



        String URL = "http://192.168.0.30:7899/api/CommonApi/Invoke";


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

//                            JSONArray json_array2 = o.getJSONArray("result");
//                            Log.e("tryyyyyyyyy",""+json_array2);
//
//                            JSONObject jsonObject = json_array2.getJSONObject(0);
                            code = o.getString("responseCode");
                            message=o.getString("responseMessage");

                            Log.e("resppppppp",""+code);


                            if (code.equalsIgnoreCase("-100"))
                            {

                                Log.e("resppppppp","ifffff"+code);

//                                JSONArray json_array2 = o.getJSONArray("result");
//
//
//                                JSONObject jsonObject = json_array2.getJSONObject(0);
//
//                                Log.e("tryyyyyyyyy","  "+jsonObject.get("UserKey"));

                                Intent intent =new Intent(Registration.this,MainActivity.class);
                                startActivity(intent);

            }
        });
    }
}
