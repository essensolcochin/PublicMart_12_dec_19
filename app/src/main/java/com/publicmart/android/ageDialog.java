package com.publicmart.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.provider.DocumentsContract;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.publicmart.android.Adapters_.AgeDialogAdapter_;

import java.util.ArrayList;

public class ageDialog extends BottomSheetDialogFragment {

    RecyclerView ages;
    ArrayList<String>age=new ArrayList<>();
    AgeDialogAdapter_ ageDialogAdapter_;
    RelativeLayout layout;

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View RootView = View.inflate(getContext(), R.layout.age_dialog, null);
        dialog.setContentView(RootView);
        ages =RootView.findViewById(R.id.age);
        layout= RootView.findViewById(R.id.lay);



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        ages.setLayoutManager(linearLayoutManager);

        for (int i=10;i<=100;i++)
        {
            age.add(Integer.toString(i));
        }

        ageDialogAdapter_ =new AgeDialogAdapter_(age,getActivity());
        ages.setAdapter(ageDialogAdapter_);


        DisplayMetrics displayMetrics = getActivity().getResources().getDisplayMetrics();

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

//        int maxHeight = (int) (height*0.88);
        int maxHeight=height/3;

        BottomSheetBehavior mBehavior = BottomSheetBehavior.from((View) RootView.getParent());
        mBehavior.setPeekHeight(maxHeight);

    }
}
