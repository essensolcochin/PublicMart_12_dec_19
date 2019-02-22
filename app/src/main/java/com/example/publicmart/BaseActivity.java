package com.example.publicmart;

import android.content.Intent;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextWatcher;

import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;



public class BaseActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    public Toolbar toolbar;
    public TextView head;
    private ImageView search,cart;
    private NavigationView navigationView;
    private ListView mSearchNFilterLv;

    private EditText mSearchEdt;

    private ArrayList<String> mStringList;

    private ValueAdapter valueAdapter;

    private TextWatcher mSearchTw;


    public Toolbar getToolBar(){
        return toolbar;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);


        head =findViewById(R.id.appname);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Helvetica.ttf");

        head.setTypeface(custom_font);




        toolbar=(Toolbar)findViewById(R.id.toolbar);
        search=(ImageView)findViewById(R.id.search);
        cart=(ImageView) findViewById(R.id.cart);

        setSupportActionBar(toolbar);




        navigationView = (NavigationView)findViewById(R.id.navigation_view);
        drawerLayout =(DrawerLayout)findViewById(R.id.drawer_layout2);
        toggle= new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.Open,R.string.Close);
        drawerLayout.addDrawerListener(toggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


//        Blurry.with(this).radius(25).sampling(2).onto(navigationView);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaseActivity.this, SearchBar.class);
                startActivity(intent);
            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                final String appPackageName = getPackageName();

                int id = item.getItemId();

                switch(id) {
                    case R.id.hom:
                        Intent intent = new Intent(BaseActivity.this, Home.class);
                        startActivity(intent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.prof:
                        intent = new Intent(BaseActivity.this, Profile.class);
                        startActivity(intent);
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.pro:
                        intent = new Intent(BaseActivity.this, Products.class);
                        startActivity(intent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.ser:
                        intent = new Intent(BaseActivity.this, Services.class);
                        startActivity(intent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.orderstat:
                        intent = new Intent(BaseActivity.this, OrderStatus.class);
                        startActivity(intent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.bookingstat:
                        intent = new Intent(BaseActivity.this, Bookingstatus.class);
                        startActivity(intent);
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.logout:
                        intent = new Intent(BaseActivity.this, MainActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawers();
                        break;

                    default:
                }



                return false;
            }
        });





    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        toggle.syncState();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {



//        if (item != null && item.getItemId() == android.R.id.home) {
//            if (drawerLayout.isDrawerOpen(Gravity.END)) {
//                drawerLayout.closeDrawer(Gravity.END);
//            }
//            else {
//                drawerLayout.openDrawer(Gravity.END);
//            }
//        }
//        return false;



        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }








}
