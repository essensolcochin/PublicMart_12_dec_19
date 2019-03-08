package com.example.publicmart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchBar extends AppCompatActivity {


    private ArrayAdapter adapter;
    TextView clk;
    EditText editText;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bar);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        ListView list = (ListView) findViewById(R.id.theList);
        editText = (EditText) findViewById(R.id.searchFilter);
        back = (ImageView) findViewById(R.id.backpress);
        back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        onBackPressed();
    }
});
        ArrayList<SpinnerModel> names = new ArrayList<>();

//        SpinnerModel items = new SpinnerModel(1,"pooru");
//        names.add(items);
//        names.add("Sarees");
//        names.add("Shoes");
//        names.add("Spices");
//        names.add("Weightloss");
//        names.add("Proteins");


        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplication(),"This is my Toast message!", Toast.LENGTH_LONG).show();
            }
        });



        adapter = new ArrayAdapter(this, R.layout.list_item_layout, names);
        list.setAdapter(adapter);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                (SearchBar.this).adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }


    }

