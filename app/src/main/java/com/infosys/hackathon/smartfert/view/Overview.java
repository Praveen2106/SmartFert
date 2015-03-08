package com.infosys.hackathon.smartfert.view;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.infosys.hackathon.smartfert.R;


public class Overview extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            return;

        getSupportActionBar().setTitle("SmartFert - Overview");
        setContentView(R.layout.form_overview);
        findViewById(R.id.captureDetails).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Overview.this, CaptureDetails.class));
            }
        });

        findViewById(R.id.manageFertility).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Overview.this, FertliserMgmtDashActivity.class));
            }
        });

        findViewById(R.id.cropAnalytics).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Overview.this, "Feature not yet implemented!", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.cropInsurance).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Overview.this, "Feature not yet implemented!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_about) {
            startActivity(new Intent(this, AboutSmartFert.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
