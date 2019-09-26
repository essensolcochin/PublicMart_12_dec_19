package com.publicmart.essensol;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;





public class ItemCountSelectionDialog extends Dialog implements View.OnClickListener {

public Activity c;
public Button confirm;
public Spinner Quantity;
public TextView BV,Rupees,name,desc;
public ImageView productImage;

    public ItemCountSelectionDialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_layout);



        confirm = (Button) findViewById(R.id.orderConfirm);
        Quantity = (Spinner) findViewById(R.id.quantity);
        BV = (TextView) findViewById(R.id.BV);
        name = (TextView) findViewById(R.id.product_name);

        Rupees = (TextView) findViewById(R.id.RS);
        productImage =(ImageView) findViewById(R.id.proImg);
        confirm.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.orderConfirm:


                c.finish();
                break;

            default:
                break;
        }
        dismiss();
    }
}



