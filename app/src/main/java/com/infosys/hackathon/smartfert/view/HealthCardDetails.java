package com.infosys.hackathon.smartfert.view;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.infosys.hackathon.smartfert.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * File Created by Praveen K on 07-Mar-15.
 */
public class HealthCardDetails extends Fragment implements  View.OnClickListener{

    Button soilSampleDate, soilAnalysisSentDate;
    Spinner soilTexture, calciumContent, saltContent, phContent;
    TextView bOC, bPC, bNC, bKC;
    EditText avlOC, avlPC, avlNC, avlKC;

    int day1, month1, year1;
    int day2, month2, year2;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView;

        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        rootView = layoutInflater.inflate(R.layout.fragment_health_card_details, null);

        soilSampleDate = (Button) rootView.findViewById(R.id.soilSampleDate);
        soilAnalysisSentDate = (Button) rootView.findViewById(R.id.soilAnalysisRptDate);
        soilTexture = (Spinner) rootView.findViewById(R.id.soilTexture);
        calciumContent = (Spinner) rootView.findViewById(R.id.calciumCarbonate);
        saltContent = (Spinner) rootView.findViewById(R.id.saltContent);
        phContent = (Spinner) rootView.findViewById(R.id.phContent);

        avlOC = (EditText) rootView.findViewById(R.id.avlOC);
        avlKC = (EditText) rootView.findViewById(R.id.avlKC);
        avlPC = (EditText) rootView.findViewById(R.id.avlPC);
        avlNC = (EditText) rootView.findViewById(R.id.avlNC);

        bOC = (TextView) rootView.findViewById(R.id.bOC);
        bNC = (TextView) rootView.findViewById(R.id.bNC);
        bPC = (TextView) rootView.findViewById(R.id.bPC);
        bKC = (TextView) rootView.findViewById(R.id.bKC);

        soilSampleDate.setOnClickListener(this);
        soilAnalysisSentDate.setOnClickListener(this);
        populateInitialValues();
        return rootView;
    }


    public void populateInitialValues() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        year1 = year2 = calendar.get(Calendar.YEAR);
        day1 = day2 = calendar.get(Calendar.DAY_OF_MONTH);
        month1 = month2 = calendar.get(Calendar.MONTH);

        soilSampleDate.setText(simpleDateFormat.format(calendar.getTime()));
        soilAnalysisSentDate.setText(simpleDateFormat.format(calendar.getTime()));
    }


    @Override
    public void onStart() {
        String[] textureArray = getResources().getStringArray(R.array.soilTexture);
        String[] calciumContentArray = getResources().getStringArray(R.array.calciumCarbonate);
        String[] saltContentArray = getResources().getStringArray(R.array.saltContent);
        String[] phContentArray = getResources().getStringArray(R.array.calciumCarbonate);

        avlOC.setText(String.valueOf(CaptureDetails.soilData.getAvailableOrganicContent()));
        avlKC.setText(String.valueOf(CaptureDetails.soilData.getAvailablePhosphateContent()));
        avlPC.setText(String.valueOf(CaptureDetails.soilData.getAvailablePotassiumContent()));
        avlNC.setText(String.valueOf(CaptureDetails.soilData.getAvailableNitrogenContent()));
        bOC.setText(String.valueOf(CaptureDetails.soilData.getBlanketOrganicContent()));
        bKC.setText(String.valueOf(CaptureDetails.soilData.getBlanketPhosphateContent()));
        bPC.setText(String.valueOf(CaptureDetails.soilData.getBlanketPotassiumContent()));
        bNC.setText(String.valueOf(CaptureDetails.soilData.getBlanketNitrogenContent()));

        try {
            for(int i = 0; i<textureArray.length;i++) {
                if(textureArray[i].equalsIgnoreCase(CaptureDetails.soilData.getSoilTexture()))
                    soilTexture.setSelection(i);
            }
        } catch (Exception e) {
            soilTexture.setSelection(0);
            e.printStackTrace();
        }

        try {
            for(int i = 0; i<calciumContentArray.length;i++) {
                if(calciumContentArray[i].equalsIgnoreCase(CaptureDetails.soilData.getCalciumCarbonateContent()))
                    calciumContent.setSelection(i);
            }
        } catch (Exception e) {
            calciumContent.setSelection(0);
            e.printStackTrace();
        }

        try {
            for(int i = 0; i<saltContentArray.length;i++) {
                if(saltContentArray[i].equalsIgnoreCase(CaptureDetails.soilData.getSaltContent()))
                    saltContent.setSelection(i);
            }
        } catch (Exception e) {
            saltContent.setSelection(0);
            e.printStackTrace();
        }

        try {
            for(int i = 0; i<phContentArray.length;i++) {
                if(phContentArray[i].equalsIgnoreCase(CaptureDetails.soilData.getpHContent()))
                    phContent.setSelection(i);
            }
        } catch (Exception e) {
            phContent.setSelection(0);
            e.printStackTrace();
        }


        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date sown = simpleDateFormat.parse(CaptureDetails.soilData.getSoilSampleDate());
            calendar.setTime(sown);
            day1 = calendar.get(Calendar.DAY_OF_MONTH);
            year1 = calendar.get(Calendar.YEAR);
            month1 = calendar.get(Calendar.MONTH);
            soilSampleDate.setText(simpleDateFormat.format(calendar.getTime()));

            Date harvest = simpleDateFormat.parse(CaptureDetails.soilData.getSoilReportSentDate());
            calendar.setTime(harvest);
            day2 = calendar.get(Calendar.DAY_OF_MONTH);
            year2 = calendar.get(Calendar.YEAR);
            month2 = calendar.get(Calendar.MONTH);
            soilAnalysisSentDate.setText(simpleDateFormat.format(calendar.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.onStart();
    }

    @Override
    public void onStop() {
        saveDetails();
        super.onStop();
    }

    public void saveDetails() {
        String[] textureArray = getResources().getStringArray(R.array.soilTexture);
        String[] calciumContentArray = getResources().getStringArray(R.array.calciumCarbonate);
        String[] saltContentArray = getResources().getStringArray(R.array.saltContent);
        String[] phContentArray = getResources().getStringArray(R.array.calciumCarbonate);

        CaptureDetails.soilData.setSoilSampleDate(soilSampleDate.getText().toString());
        CaptureDetails.soilData.setSoilReportSentDate(soilAnalysisSentDate.getText().toString());
        CaptureDetails.soilData.setSoilTexture(textureArray[soilTexture.getSelectedItemPosition()]);
        CaptureDetails.soilData.setCalciumCarbonateContent(calciumContentArray[calciumContent.getSelectedItemPosition()]);
        CaptureDetails.soilData.setSaltContent(saltContentArray[saltContent.getSelectedItemPosition()]);
        CaptureDetails.soilData.setpHContent(phContentArray[phContent.getSelectedItemPosition()]);

        CaptureDetails.soilData.setAvailableNitrogenContent(Float.valueOf(avlNC.getText().toString()));
        CaptureDetails.soilData.setAvailableOrganicContent(Float.valueOf(avlOC.getText().toString()));
        CaptureDetails.soilData.setAvailablePhosphateContent(Float.valueOf(avlKC.getText().toString()));
        CaptureDetails.soilData.setAvailablePotassiumContent(Float.valueOf(avlPC.getText().toString()));

        CaptureDetails.soilData.setBlanketNitrogenContent(Float.valueOf(bNC.getText().toString()));
        CaptureDetails.soilData.setBlanketOrganicContent(Float.valueOf(bOC.getText().toString()));
        CaptureDetails.soilData.setBlanketPhosphateContent(Float.valueOf(bKC.getText().toString()));
        CaptureDetails.soilData.setBlanketPotassiumContent(Float.valueOf(bPC.getText().toString()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.soilSampleDate:
                DatePickerDialog datePickerDialog1 = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        month1 = monthOfYear;
                        day1 = dayOfMonth;
                        year1 = year;

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        soilSampleDate.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                }, year1, month1, day1);

                datePickerDialog1.show();
                break;

            case R.id.soilAnalysisRptDate:
                DatePickerDialog datePickerDialog2 = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        month2 = monthOfYear;
                        day2 = dayOfMonth;
                        year2 = year;

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        soilAnalysisSentDate.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                }, year2, month2, day2);

                datePickerDialog2.show();
                break;
        }
    }
}
