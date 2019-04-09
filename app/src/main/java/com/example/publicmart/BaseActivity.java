package com.example.publicmart;

import android.content.Intent;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextWatcher;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;


import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.IOException;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;


public class BaseActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    public Toolbar toolbar;
    public TextView head,prof,profname,number,memtype;
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

    private RealmResults<RealmShopModel> cartSIZE;
    private Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);


        head =findViewById(R.id.appname);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/RalewayBold.ttf");

        realm = Realm.getDefaultInstance();


        cartSIZE = realm.where(RealmShopModel.class).findAll();
        cartSIZE.load();

        Log.e("checkinggggggggg","  inside cart "+cartSIZE);

        head.setTypeface(custom_font);



//        head.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(BaseActivity.this, Home.class);
//                startActivity(intent);
//            }
//        });





        toolbar=(Toolbar)findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);




        navigationView = (NavigationView)findViewById(R.id.navigation_view);
        drawerLayout =(DrawerLayout)findViewById(R.id.drawer_layout2);
        toggle= new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.Open,R.string.Close);
        drawerLayout.addDrawerListener(toggle);


        SharedPreferences sp = getSharedPreferences("UserLog",MODE_PRIVATE);


        View headerView = navigationView.getHeaderView(0);
        prof = (TextView) headerView.findViewById(R.id.profPic);
        profname = (TextView) headerView.findViewById(R.id.profName);
        number = (TextView) headerView.findViewById(R.id.profNo);
        memtype = (TextView) headerView.findViewById(R.id.menType);

        profname.setText(sp.getString("CustomerName",null));
        number.setText(sp.getString("CustCode",null));
        prof.setText(sp.getString("CustomerName",null).substring(0,1));
        memtype.setText(sp.getString("MemberShip",null));


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                final String appPackageName = getPackageName();

                int id = item.getItemId();

                switch(id) {
                    case R.id.hom:
                        Intent intent = new Intent(BaseActivity.this, Home.class);
                        startActivity(intent);
                        finish();
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

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    FirebaseInstanceId.getInstance().deleteInstanceId();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
//                        SharedPreferences SaveToken =   getSharedPreferences("GetToken",MODE_PRIVATE);
//                        SharedPreferences.Editor edit =SaveToken.edit();
//                        edit.putString("Token","");
//                        edit.apply();


                        SharedPreferences sp = getSharedPreferences("UserLog",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putBoolean("LoggedUser",false);
                        editor.apply();
                        realm.beginTransaction();
                        cartSIZE.deleteAllFromRealm();
                        realm.commitTransaction();
                        realm.close();
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
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem cart = menu.findItem(R.id.cart_action);
        View actionView = MenuItemCompat.getActionView(cart);


        FrameLayout click =(FrameLayout)actionView.findViewById(R.id.cartclick);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               

                Intent cartIntent = new Intent(BaseActivity.this, OrderStatus.class);

                startActivity(cartIntent);
                finish();
            }
        });

                TextView  cartItem = (TextView) actionView.findViewById(R.id.cart_badge);

        if(cartSIZE.size()==0)
        {
            cartItem.setVisibility(View.GONE);
        }
        else
        {
            cartItem.setVisibility(View.VISIBLE);
            Log.e("checkinggggggggg","  inside cart "+cartSIZE.get(0).getCount());

            for (int i =0;i<cartSIZE.size();i++)
            cartItem.setText(cartSIZE.get(i).getCount());
        }

        return  true;
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }


        switch (item.getItemId())
        {
            case R.id.search_action:
                Log.e("checkinggggggggg","  inside cart ");

                Intent intent = new Intent(BaseActivity.this, SearchBar.class);
               startActivity(intent);
               break;



        }

        return super.onOptionsItemSelected(item);
    }

}
