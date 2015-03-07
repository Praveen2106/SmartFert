package com.infosys.hackathon.smartfert.view;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.infosys.hackathon.smartfert.R;

/**
 * File Created by Praveen K on 05-Mar-15.
 */
public class CaptureDetails extends FragmentActivity {

    FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            return;

        setContentView(R.layout.form_capture_details);
        Fragment basicDetails = new BasicDetails();

        fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frame_container, basicDetails).commit();



        findViewById(R.id.bankDetails).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment basicDetails = new BasicDetails();
                fm.beginTransaction().replace(R.id.frame_container, basicDetails).commit();
            }
        });


        findViewById(R.id.landDetails).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment landDetails = new LandDetails();
                fm.beginTransaction().replace(R.id.frame_container, landDetails).commit();
            }
        });


        findViewById(R.id.soilDetails).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment soilDetails = new SoilDetails();
                fm.beginTransaction().replace(R.id.frame_container, soilDetails).commit();
            }
        });

        findViewById(R.id.healthCardDetails).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment healthCardDetails = new HealthCardDetails();
                fm.beginTransaction().replace(R.id.frame_container, healthCardDetails).commit();
            }
        });
    }
}
