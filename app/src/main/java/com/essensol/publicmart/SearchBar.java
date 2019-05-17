package com.essensol.publicmart;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
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
    SearchView editText;
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
        editText = (SearchView) findViewById(R.id.search);
        back = (ImageView) findViewById(R.id.backpress);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

//        ArrayList<String> names = new ArrayList<>();
//        names.add("Sarees");
//        names.add("Shoes");
//        names.add("Spices");
//        names.add("Weightloss");
//        names.add("Proteins");

        names2 = new ArrayList<>();


//        editText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                Toast.makeText(getApplication(), "This is my Toast message!",
//                        Toast.LENGTH_LONG).show();
//            }
//        });

        searchitems();


        if (names2.isEmpty()) {

            Log.e("Arraylist", "" + names2);

            }
    else {
            editText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {

                    list.setVisibility(View.VISIBLE);

                    if (names2.contains(query)) {
                        adapter.filter(query);
                    } else {
                        Toast.makeText(SearchBar.this, "No Match found", Toast.LENGTH_LONG).show();
                    }
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    list.setVisibility(View.VISIBLE);
                    adapter.filter(newText);
                    return false;
                }
            });

        }
        EditText searchEditText = (EditText) editText.findViewById(android.support.v7.appcompat.R.id.search_src_text);

        searchEditText.setTextColor(Color.DKGRAY);
        searchEditText.setHintTextColor(Color.DKGRAY);


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

                                    Log.e("resppppppp", "ifffff" + code);


                                    names2.add(items);


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



