package com.infosys.hackathon.smartfert;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

/**
 * File Created by Praveen K on 05-Mar-15.
 */
public class CaptureDetails extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            return;

        setContentView(R.layout.form_capture_details);
    }
}
