package com.infosys.hackathon.smartfert.view;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.infosys.hackathon.smartfert.R;

/**
 * File Created by Praveen K on 08-Mar-15.
 */
public class AboutSmartFert extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            return;

        getSupportActionBar().setTitle("About SmartFert");
        setContentView(R.layout.form_about);
    }
}
