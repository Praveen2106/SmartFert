package com.infosys.hackathon.smartfert.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.infosys.hackathon.smartfert.R;
import com.infosys.hackathon.smartfert.data.FarmerData;
import com.infosys.hackathon.smartfert.data.LandData;
import com.infosys.hackathon.smartfert.data.SoilData;
import com.infosys.hackathon.smartfert.data.SoilFertilityData;
import com.infosys.hackathon.smartfert.http.DataParser;
import com.infosys.hackathon.smartfert.utils.HeaderUtil;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * File Created by Praveen K on 05-Mar-15.
 */
public class CaptureDetails extends ActionBarActivity {

    FragmentManager fm;
    public static FarmerData farmerData;
    public static LandData landData;
    public static SoilData soilData;
    public static SoilFertilityData soilFertilityData;

    public static int tabNumber = -1;
    public static boolean fetchOrAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            return;

        getSupportActionBar().setTitle("Soil Fertility Capture");
        setContentView(R.layout.form_capture_details);
        final Fragment basicDetails = new BasicDetails();

        farmerData = new FarmerData();
        soilData = new SoilData();
        landData = new LandData();
        soilFertilityData = new SoilFertilityData();

        final AlertDialog dialog;
        Button addNew, fetchDetails;
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_farmer_id, null);
        dialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .create();

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        final EditText soilTestRptNo = (EditText) dialogView.findViewById(R.id.soilTestRptNo);
        addNew = (Button) dialogView.findViewById(R.id.addNewSoilReport);
        fetchDetails = (Button) dialogView.findViewById(R.id.fetchDetails);

        addNew.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                soilData.setSoilReportNumber(soilTestRptNo.getText().toString());
                fm = getSupportFragmentManager();
                tabNumber = 1;
                findViewById(R.id.bankDetails).setBackground(getDrawable(R.drawable.bg_button_1));
                findViewById(R.id.landDetails).setBackground(getDrawable(R.drawable.bg_button_2));
                findViewById(R.id.soilDetails).setBackground(getDrawable(R.drawable.bg_button_2));
                findViewById(R.id.healthCardDetails).setBackground(getDrawable(R.drawable.bg_button_2));
                fm.beginTransaction().replace(R.id.frame_container, basicDetails, "1").commit();
                fetchOrAdd = true;
                dialog.dismiss();
            }
        });

        fetchDetails.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    if(soilTestRptNo.getText().toString().equals("")) {
                        Toast.makeText(CaptureDetails.this, "Enter the Soil Test Report Number", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    soilData.setSoilReportNumber(soilTestRptNo.getText().toString());
                    InputStream ins = new ByteArrayInputStream(HeaderUtil.getFileData(soilData.getSoilReportNumber().toLowerCase(), getApplicationContext()).getBytes());
                    //InputStream ins = getResources().openRawResource(getResources().getIdentifier("raw/report1","raw", getPackageName()));

                    SAXParserFactory factory = SAXParserFactory.newInstance();
                    SAXParser saxParser = factory.newSAXParser();
                    DataParser parser = new DataParser();
                    saxParser.parse(ins, parser);

                    SoilFertilityData fData = parser.getFertilityDetails();
                    //parser.parseXMLToFile(fData.getSoilDetails().getSoilReportNumber(), "Something", getApplicationContext());
                    CaptureDetails.farmerData = fData.getFarmerDetails();
                    CaptureDetails.landData = fData.getLandDetails();
                    CaptureDetails.soilData = fData.getSoilDetails();

                    fm = getSupportFragmentManager();
                    tabNumber = 1;
                    findViewById(R.id.bankDetails).setBackground(getDrawable(R.drawable.bg_button_1));
                    findViewById(R.id.landDetails).setBackground(getDrawable(R.drawable.bg_button_2));
                    findViewById(R.id.soilDetails).setBackground(getDrawable(R.drawable.bg_button_2));
                    findViewById(R.id.healthCardDetails).setBackground(getDrawable(R.drawable.bg_button_2));
                    fm.beginTransaction().replace(R.id.frame_container, basicDetails, "1").commit();
                    fetchOrAdd = true;
                    dialog.dismiss();
                } catch (Exception e) {
                    Toast.makeText(CaptureDetails.this, "Soil Test Report Number is invalid", Toast.LENGTH_LONG).show();
                    e.printStackTrace(); }
            }
        });

        fetchOrAdd = false;
        dialog.show();

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if(fetchOrAdd)
                    return;

                finish();
            }
        });

        findViewById(R.id.bankDetails).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment basicDetails = new BasicDetails();
                tabNumber = 1;
                findViewById(R.id.bankDetails).setBackground(getDrawable(R.drawable.bg_button_1));
                findViewById(R.id.landDetails).setBackground(getDrawable(R.drawable.bg_button_2));
                findViewById(R.id.soilDetails).setBackground(getDrawable(R.drawable.bg_button_2));
                findViewById(R.id.healthCardDetails).setBackground(getDrawable(R.drawable.bg_button_2));

                fm.beginTransaction().replace(R.id.frame_container, basicDetails, "1").commit();
            }
        });


        findViewById(R.id.landDetails).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment landDetails = new LandDetails();
                tabNumber = 2;
                findViewById(R.id.bankDetails).setBackground(getDrawable(R.drawable.bg_button_2));
                findViewById(R.id.landDetails).setBackground(getDrawable(R.drawable.bg_button_1));
                findViewById(R.id.soilDetails).setBackground(getDrawable(R.drawable.bg_button_2));
                findViewById(R.id.healthCardDetails).setBackground(getDrawable(R.drawable.bg_button_2));
                fm.beginTransaction().replace(R.id.frame_container, landDetails, "2").commit();
            }
        });


        findViewById(R.id.soilDetails).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment soilDetails = new SoilDetails();
                tabNumber = 3;
                findViewById(R.id.bankDetails).setBackground(getDrawable(R.drawable.bg_button_2));
                findViewById(R.id.landDetails).setBackground(getDrawable(R.drawable.bg_button_2));
                findViewById(R.id.soilDetails).setBackground(getDrawable(R.drawable.bg_button_1));
                findViewById(R.id.healthCardDetails).setBackground(getDrawable(R.drawable.bg_button_2));
                fm.beginTransaction().replace(R.id.frame_container, soilDetails, "3").commit();
            }
        });

        findViewById(R.id.healthCardDetails).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment healthCardDetails = new HealthCardDetails();
                tabNumber = 4;
                findViewById(R.id.bankDetails).setBackground(getDrawable(R.drawable.bg_button_2));
                findViewById(R.id.landDetails).setBackground(getDrawable(R.drawable.bg_button_2));
                findViewById(R.id.soilDetails).setBackground(getDrawable(R.drawable.bg_button_2));
                findViewById(R.id.healthCardDetails).setBackground(getDrawable(R.drawable.bg_button_1));
                fm.beginTransaction().replace(R.id.frame_container, healthCardDetails, "4").commit();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_save) {
            SoilFertilityData soilFertilityData = new SoilFertilityData();

            if (tabNumber == 1) {
                BasicDetails fragment = (BasicDetails) getSupportFragmentManager().findFragmentByTag("1");
                fragment.saveDetails();
            } else if (tabNumber == 2) {
                LandDetails fragment = (LandDetails) getSupportFragmentManager().findFragmentByTag("2");
                fragment.saveDetails();
            }else if(tabNumber == 3) {
                SoilDetails fragment = (SoilDetails) getSupportFragmentManager().findFragmentByTag("3");
                fragment.saveDetails();
            } else {
                HealthCardDetails fragment = (HealthCardDetails) getSupportFragmentManager().findFragmentByTag("4");
                fragment.saveDetails();
            }

            soilFertilityData.setFarmerDetails(CaptureDetails.farmerData);
            soilFertilityData.setLandDetails(CaptureDetails.landData);
            soilFertilityData.setSoilDetails(CaptureDetails.soilData);

            HeaderUtil.toXML(soilFertilityData, getApplicationContext());
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
