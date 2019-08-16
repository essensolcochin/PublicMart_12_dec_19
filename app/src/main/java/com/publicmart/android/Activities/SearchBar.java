package com.publicmart.android.Activities;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.publicmart.android.R;
import com.publicmart.android.ModelClasses.SearchFilterModel;
import com.publicmart.android.Adapters_._CustomArrayAdapterSearch_;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchBar extends AppCompatActivity {

    Integer StationKey;
    String StationName, ShortCode;

    private _CustomArrayAdapterSearch_ adapter;
    TextView clk;
    EditText editText;
    ImageView back;
    JSONObject jsonString;
    ListView list;
    ArrayList<SearchFilterModel> names2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bar);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        list = (ListView) findViewById(R.id.theList);
        SearchManager searchManager = (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);
        editText = (EditText) findViewById(R.id.search);
        back = (ImageView) findViewById(R.id.backpress);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        names2 = new ArrayList<>();




        searchitems();

        Log.e("Search", "else " );
        if (names2.isEmpty()) {

            Log.e("Arraylist", "" + names2);

            }
         else {

            Log.e("Search", "else " );


            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.e("onTextChanged", "else "+s );
                    if (names2.contains(s.toString())) {
                        adapter.filter(s.toString());
                    } else {
                        Toast.makeText(SearchBar.this, "No Match found", Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

//            editText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//                @Override
//                public boolean onQueryTextSubmit(String query) {
//                    Log.e("onQueryTextSubmit", "" + names2.contains(query));
//
//                    list.setVisibility(View.VISIBLE);
//
//                    if (names2.contains(query)) {
//                        adapter.filter(query);
//                    } else {
//                        Toast.makeText(SearchBar.this, "No Match found", Toast.LENGTH_LONG).show();
//                    }
//                    return false;
//                }
//
//                @Override
//                public boolean onQueryTextChange(String newText) {
//                    Log.e("onQueryTextChange", "" + names2.contains(newText));
//                    list.setVisibility(View.VISIBLE);
//                    adapter.filter(newText);
//                    return false;
//                }
//            });

        }



//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//
////                Intent intent = new Intent(SearchBar.this, Fashion.class);
////                intent.putExtra()
////                startActivity(intent);
//
//                Toast.makeText(getApplicationContext(), adapter.getItem(position).toString(), Toast.LENGTH_LONG).show();
//
//
//            }
//        });


    }


    private void searchitems() {


        try {
            JSONObject values = new JSONObject();
            values.put("CategoryKey", 1);
            jsonString = new JSONObject();
            jsonString.put("Token", "0001");
            jsonString.put("call", "GetActiveProductListForSearch");
            jsonString.put("values", values);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        String URL = this.getString(R.string.Url) + "Select";


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Log.e("Jsonnnn", "" + response);


                        try {


                            JSONObject o = new JSONObject(response);

                            ////// Checking Json Response Is JSON Object Or Not ///////
//                            String data = response;
//                            Object json = new JSONTokener(data).nextValue();
//                            if (json instanceof JSONObject){
//
//                                Log.e("objectttttt",""+json);
//                            }
//
//                            //you have an object
//                            else if (json instanceof JSONArray){
//                                Log.e("Arrayyyyyyy",""+json);
//                            }

                            ///////////////////////////////////////////

                            Log.e("tryyyyyyyyy", "in" + o);


                            String code = o.getString("responseCode");
                            String message = o.getString("responseMessage");


                            if (code.equalsIgnoreCase("0")) {

                                JSONArray json_array2 = o.getJSONArray("result");
                                JSONObject jsonObject;

                                int j;
                                for (j = 0; j < json_array2.length(); j++) {
                                    jsonObject = json_array2.getJSONObject(j);


                                    SearchFilterModel items = new SearchFilterModel(jsonObject.getString("ProductKey"),
                                            jsonObject.getString("CategoryKey"),
                                            jsonObject.getString("CategoryName"),
                                            jsonObject.getString("BrandName"),
                                            jsonObject.getString("ShortDesc"));




                                    names2.add(items);
                                    Log.e("resppppppp", "arraylist" + names2.size());

                                }
                                adapter = new _CustomArrayAdapterSearch_(SearchBar.this,  names2);
                                list.setAdapter(adapter);

                            }
//                            else {
//                                Toast.makeText(SearchBar.this, "Coming Shortly", Toast.LENGTH_SHORT).show();
//                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // progress.cancel();
                        Toast.makeText(getApplicationContext(), "Some Error Occurred ", Toast.LENGTH_SHORT).show();

                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("jsonString", jsonString.toString());
                Log.e("paramssss", "" + param);
                return param;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("Content-Type", "application/x-www-form-urlencoded");
                return param;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
}




