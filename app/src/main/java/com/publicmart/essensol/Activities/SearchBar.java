package com.publicmart.essensol.Activities;

import android.app.SearchManager;
import android.content.Context;
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

import com.publicmart.essensol.R;
import com.publicmart.essensol.ModelClasses.SearchFilterModel;
import com.publicmart.essensol.Adapters_._CustomArrayAdapterSearch_;
import com.publicmart.essensol.RetrofitUtils.ApiClient;
import com.publicmart.essensol.RetrofitUtils.ApiInterface;
import com.publicmart.essensol.RetrofitUtils.RetrofitResponseClasses.SearchResponse;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

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
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bar);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        list = (ListView) findViewById(R.id.theList);
        SearchManager searchManager = (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);
        editText = (SearchView) findViewById(R.id.search);
        back = (ImageView) findViewById(R.id.backpress);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        names2 = new ArrayList<>();


        apiInterface= ApiClient.getClient().create(ApiInterface.class);








        GetItemstoSearch();

        Log.e("Search", "else " );












    }






    private  void  GetItemstoSearch() {



        apiInterface.GetItemstoSearch().enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, retrofit2.Response<SearchResponse> response) {

                if (response.isSuccessful() && response.code() == 200) {
                    assert response.body() != null;
                    if (response.body().getCode().equalsIgnoreCase("0")) {

                        List<SearchResponse.ResultArray> result = response.body().getResponse();



                        for (int i = 0; i < result.size(); i++) {

                            SearchFilterModel items = new SearchFilterModel(result.get(i).getProductKey(),
                                    result.get(i).getCategoryKey(),
                                    result.get(i).getCategoryName(),
                                    result.get(i).getBrandName(),
                                    result.get(i).getShortDesc());




                            names2.add(items);


                        }
                        Log.e("resppppppp", "arraylist" + names2.size());
                        adapter = new _CustomArrayAdapterSearch_(SearchBar.this,  names2);
                        list.setAdapter(adapter);


                        SearchFilter();


                    }

                }

                else if(response.code() == 401) {

                    Log.e("Error  Codeeeeeeeeeeee","  "+response.code());
                }

                else if( response.code() == 500) {

                    Log.e("Error  Codeeeeeeeeeeee","  "+response.code());
                }

                else if(response.code() == 408) {

                    Log.e("Error  Codeeeeeeeeeeee","  "+response.code());
                }




            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {

            }
        });


    }

    private  void SearchFilter() {
        if (names2.isEmpty()) {

            Log.e("Arraylist", "" + names2);

        }
        else {

            Log.e("Search", "else " );

            editText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Log.e("onQueryTextSubmit", "" + names2.contains(query));

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
                    Log.e("onQueryTextChange", "" + names2.contains(newText));
                    list.setVisibility(View.VISIBLE);
                    adapter.filter(newText);
                    return false;
                }
            });

        }
        EditText searchEditText = (EditText) editText.findViewById(android.support.v7.appcompat.R.id.search_src_text);

        searchEditText.setTextColor(Color.DKGRAY);
        searchEditText.setHintTextColor(Color.DKGRAY);


    }

}




