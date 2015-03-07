package com.infosys.hackathon.smartfert.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.infosys.hackathon.smartfert.R;

/**
 * File Created by Praveen K on 07-Mar-15.
 */
public class SoilDetails extends Fragment {

    EditText soilTestRptNo, soilLabNo;
    Spinner irrigationType, currentCrop, climaticZone, cropRotation;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView;

        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        rootView = layoutInflater.inflate(R.layout.fragment_soil_details, null);

        soilTestRptNo = (EditText) rootView.findViewById(R.id.soilTestRptNo);
        soilLabNo = (EditText) rootView.findViewById(R.id.soilLabNo);
        irrigationType = (Spinner) rootView.findViewById(R.id.irrigationType);
        currentCrop = (Spinner) rootView.findViewById(R.id.currentCrop);
        climaticZone = (Spinner) rootView.findViewById(R.id.climaticZone);
        cropRotation = (Spinner) rootView.findViewById(R.id.cropRotation);

        return rootView;
    }


    @Override
    public void onStart() {
        soilTestRptNo.setText(CaptureDetails.soilData.getSoilReportNumber());
        soilLabNo.setText(CaptureDetails.soilData.getSoilLabNumber());

        String[] irrigationArray = getResources().getStringArray(R.array.irrigationType);
        String[] currentCropArray = getResources().getStringArray(R.array.currentCrop);
        String[] climateArray = getResources().getStringArray(R.array.climaticZone);
        String[] cropRotationArray = getResources().getStringArray(R.array.cropRotation);

        try {
            for(int i = 0; i<irrigationArray.length;i++) {
                if(irrigationArray[i].equalsIgnoreCase(CaptureDetails.soilData.getIrrigationType()))
                    irrigationType.setSelection(i);
            }
        } catch (Exception e) {
            irrigationType.setSelection(0);
            e.printStackTrace();
        }


        try {
            for(int i = 0; i<currentCropArray.length;i++) {
                if(currentCropArray[i].equalsIgnoreCase(CaptureDetails.soilData.getCurrentCrop()))
                    currentCrop.setSelection(i);
            }
        } catch (Exception e) {
            currentCrop.setSelection(0);
            e.printStackTrace();
        }

        try {
            for(int i = 0; i<climateArray.length;i++) {
                if(climateArray[i].equalsIgnoreCase(CaptureDetails.soilData.getClimaticZone()))
                    climaticZone.setSelection(i);
            }
        } catch (Exception e) {
            climaticZone.setSelection(0);
            e.printStackTrace();
        }

        try {
            for(int i = 0; i<cropRotationArray.length;i++) {
                if(cropRotationArray[i].equalsIgnoreCase(CaptureDetails.soilData.getCropRotation()))
                    cropRotation.setSelection(i);
            }
        } catch (Exception e) {
            cropRotation.setSelection(0);
            e.printStackTrace();
        }

        super.onStart();
    }

    @Override
    public void onStop() {
        String[] irrigationArray = getResources().getStringArray(R.array.irrigationType);
        String[] currentCropArray = getResources().getStringArray(R.array.currentCrop);
        String[] climateArray = getResources().getStringArray(R.array.climaticZone);
        String[] cropRotationArray = getResources().getStringArray(R.array.cropRotation);

        CaptureDetails.soilData.setSoilReportNumber(soilTestRptNo.getText().toString());
        CaptureDetails.soilData.setSoilLabNumber(soilLabNo.getText().toString());
        CaptureDetails.soilData.setIrrigationType(irrigationArray[irrigationType.getSelectedItemPosition()]);
        CaptureDetails.soilData.setCurrentCrop(currentCropArray[currentCrop.getSelectedItemPosition()]);
        CaptureDetails.soilData.setClimaticZone(climateArray[climaticZone.getSelectedItemPosition()]);
        CaptureDetails.soilData.setCropRotation(cropRotationArray[cropRotation.getSelectedItemPosition()]);
        super.onStop();
    }
}
