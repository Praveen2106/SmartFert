package com.infosys.hackathon.smartfert.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.infosys.hackathon.smartfert.R;

/**
 * File Created by Praveen K on 07-Mar-15.
 */
public class LandDetails extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView;

        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        rootView = layoutInflater.inflate(R.layout.fragment_land_details, null);
        return rootView;
    }
}
