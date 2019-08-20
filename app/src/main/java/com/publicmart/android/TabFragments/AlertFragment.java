package com.publicmart.android.TabFragments;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.publicmart.android.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlertFragment extends DialogFragment {


    public AlertFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.fragment_alert, container, false);

        this.getDialog().setCancelable(false);

        return RootView;
    }
    


}
