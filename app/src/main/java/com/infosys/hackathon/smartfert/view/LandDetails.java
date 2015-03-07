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

import com.infosys.hackathon.smartfert.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * File Created by Praveen K on 07-Mar-15.
 */
public class LandDetails extends Fragment implements View.OnClickListener{

    EditText surveyNo, landArea, district, zonal, village, currentCrop, previousCrop;
    Button cropSownDate,cropHarvestDate;
    Spinner ownership;

    int sownDate, sownMonth, sownYear;
    int harvestDate, harvestMonth, harvestYear;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView;

        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        rootView = layoutInflater.inflate(R.layout.fragment_land_details, null);

        cropSownDate = (Button) rootView.findViewById(R.id.cropSownDate);
        cropHarvestDate = (Button) rootView.findViewById(R.id.cropHarvestDate);

        surveyNo = (EditText) rootView.findViewById(R.id.surveyNo);
        landArea = (EditText) rootView.findViewById(R.id.landArea);
        district = (EditText) rootView.findViewById(R.id.district);
        zonal = (EditText) rootView.findViewById(R.id.zonal);
        village = (EditText) rootView.findViewById(R.id.village);
        currentCrop = (EditText) rootView.findViewById(R.id.currentCrop);
        previousCrop = (EditText) rootView.findViewById(R.id.previousCrop);
        ownership = (Spinner) rootView.findViewById(R.id.ownership);

        cropSownDate.setOnClickListener(this);
        cropHarvestDate.setOnClickListener(this);

        populateInitialValues();
        return rootView;
    }

    @Override
    public void onStart() {
        surveyNo.setText(CaptureDetails.landData.getSurveyName());
        landArea.setText(String.valueOf(CaptureDetails.landData.getLandArea()));
        district.setText(CaptureDetails.landData.getDistrict());
        zonal.setText(CaptureDetails.landData.getZonal());
        village.setText(CaptureDetails.landData.getVillage());
        currentCrop.setText(CaptureDetails.landData.getCurrentCrop());
        previousCrop.setText(CaptureDetails.landData.getPreviousCrop());

        try {
            if(CaptureDetails.landData.getOwnership().equals("Own"))
                ownership.setSelection(0);
            else if(CaptureDetails.landData.getOwnership().equals("Leased"))
                ownership.setSelection(1);
            else
                ownership.setSelection(2);
        } catch (Exception e) {
            ownership.setSelection(0);
            e.printStackTrace();
        }


        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date sown = simpleDateFormat.parse(CaptureDetails.landData.getCropDate());
            calendar.setTime(sown);
            sownDate = calendar.get(Calendar.DAY_OF_MONTH);
            sownYear = calendar.get(Calendar.YEAR);
            sownMonth = calendar.get(Calendar.MONTH);
            cropSownDate.setText(simpleDateFormat.format(calendar.getTime()));

            Date harvest = simpleDateFormat.parse(CaptureDetails.landData.getHarvestDate());
            calendar.setTime(harvest);
            harvestYear = calendar.get(Calendar.YEAR);
            harvestDate = calendar.get(Calendar.DAY_OF_MONTH);
            harvestMonth = calendar.get(Calendar.MONTH);
            cropHarvestDate.setText(simpleDateFormat.format(calendar.getTime()));
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
        CaptureDetails.landData.setSurveyName(surveyNo.getText().toString());
        CaptureDetails.landData.setLandArea(Float.parseFloat(landArea.getText().toString()));
        CaptureDetails.landData.setDistrict(district.getText().toString());
        CaptureDetails.landData.setZonal(zonal.getText().toString());
        CaptureDetails.landData.setVillage(village.getText().toString());
        CaptureDetails.landData.setCurrentCrop(currentCrop.getText().toString());
        CaptureDetails.landData.setPreviousCrop(previousCrop.getText().toString());
        CaptureDetails.landData.setCropDate(cropSownDate.getText().toString());
        CaptureDetails.landData.setHarvestDate(cropHarvestDate.getText().toString());

        switch (ownership.getSelectedItemPosition()) {
            case 0:
                CaptureDetails.landData.setOwnership("Own");
                break;
            case 1:
                CaptureDetails.landData.setOwnership("Leased");
                break;
            case 2:
                CaptureDetails.landData.setOwnership("Rented");
                break;
        }
    }

    public void populateInitialValues() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        sownYear = harvestYear = calendar.get(Calendar.YEAR);
        sownDate = harvestDate = calendar.get(Calendar.DAY_OF_MONTH);
        sownMonth = harvestMonth = calendar.get(Calendar.MONTH);

        cropSownDate.setText(simpleDateFormat.format(calendar.getTime()));
        cropHarvestDate.setText(simpleDateFormat.format(calendar.getTime()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cropSownDate:
                DatePickerDialog sownDateBuilder = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        sownMonth = monthOfYear;
                        sownDate = dayOfMonth;
                        sownYear = year;

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        cropSownDate.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                }, sownYear, sownMonth, sownDate);

                sownDateBuilder.show();
                break;

            case R.id.cropHarvestDate:
                DatePickerDialog harvestDateBuilder = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        harvestMonth = monthOfYear;
                        harvestDate = dayOfMonth;
                        harvestYear = year;

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        cropHarvestDate.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                }, harvestYear, harvestMonth, harvestDate);

                harvestDateBuilder.show();
                break;
        }
    }
}
