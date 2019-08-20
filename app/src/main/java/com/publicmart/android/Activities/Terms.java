package com.publicmart.android.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.publicmart.android.R;

public class Terms extends AppCompatActivity {

    WebView terms;
    ImageView image;
    ProgressBar Progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);


        terms=findViewById(R.id.terms);
        image=findViewById(R.id.backpress);
        Progress=findViewById(R.id.progress);


        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        terms.getSettings().setLoadsImagesAutomatically(true);
        terms.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        terms.loadUrl("http://www.publicmart.in/termsandconditions.html");
//        terms.loadUrl("http://service.anywherematrimony.com/privacypolicy.html");



        terms.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                Progress.setProgress(progress);
                if (progress == 100) {
                    Progress.setVisibility(View.GONE);

                } else {
                    Progress.setVisibility(View.VISIBLE);

                }
            }
        });

    }
}
