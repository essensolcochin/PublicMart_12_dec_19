package com.example.publicmart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

public class ProductView extends BaseActivity {

    TextView txtxmpny;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_product_view, contentFrameLayout);


//        android.support.v7.widget.Toolbar tb=getToolBar();
//        txtxmpny=(TextView)tb.findViewById(R.id.appname);
//        txtxmpny.setText("Products");
    }
}
