package com.infosys.hackathon.smartfert.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.infosys.hackathon.smartfert.R;

/**
 * File Created by Praveen K on 07-Mar-15.
 */
public class BasicDetails extends Fragment {

    TextView farmerId;
    EditText farmerName, fatherName, address, mobileNo, emailId, kissanCardNo, aadharCardNo, bankName, branchName, bankAccountNo, accountHolderName;
    RadioGroup sex;
    RadioButton male, female;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView;

        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        rootView = layoutInflater.inflate(R.layout.fragment_basic_details, null);

        farmerId = (TextView) rootView.findViewById(R.id.farmerId);
        farmerName = (EditText) rootView.findViewById(R.id.farmerName);
        fatherName = (EditText) rootView.findViewById(R.id.fatherName);
        address = (EditText) rootView.findViewById(R.id.address);
        mobileNo = (EditText) rootView.findViewById(R.id.mobileNo);
        emailId = (EditText) rootView.findViewById(R.id.emailId);
        kissanCardNo = (EditText) rootView.findViewById(R.id.kissanCardNo);
        aadharCardNo = (EditText) rootView.findViewById(R.id.aadharCardNo);
        bankName = (EditText) rootView.findViewById(R.id.bankName);
        branchName = (EditText) rootView.findViewById(R.id.branchName);
        bankAccountNo = (EditText) rootView.findViewById(R.id.bankAccountNo);
        accountHolderName = (EditText) rootView.findViewById(R.id.accountHolderName);

        sex = (RadioGroup) rootView.findViewById(R.id.sex);
        male = (RadioButton) rootView.findViewById(R.id.male);
        female = (RadioButton) rootView.findViewById(R.id.female);
        return rootView;
    }

    @Override
    public void onStart() {
        farmerId.setText(CaptureDetails.farmerData.getFarmerId());
        farmerName.setText(CaptureDetails.farmerData.getFarmerName());
        fatherName.setText(CaptureDetails.farmerData.getFatherName());
        address.setText(CaptureDetails.farmerData.getAddress());
        mobileNo.setText(CaptureDetails.farmerData.getMobileNumber());
        emailId.setText(CaptureDetails.farmerData.getEmail());
        kissanCardNo.setText(CaptureDetails.farmerData.getKissanCardNumber());
        aadharCardNo.setText(CaptureDetails.farmerData.getAadharNumber());
        bankName.setText(CaptureDetails.farmerData.getBankName());
        branchName.setText(CaptureDetails.farmerData.getBankBranch());
        bankAccountNo.setText(CaptureDetails.farmerData.getAccountNumber());
        accountHolderName.setText(CaptureDetails.farmerData.getAccountHolderName());

        try {
            if(CaptureDetails.farmerData.getSex().equals("M"))
                male.setChecked(true);
            else
                female.setChecked(true);
        } catch (Exception e) {
            male.setChecked(true);
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
        CaptureDetails.farmerData.setFarmerName(farmerName.getText().toString());
        CaptureDetails.farmerData.setFatherName(fatherName.getText().toString());
        CaptureDetails.farmerData.setAddress(address.getText().toString());
        CaptureDetails.farmerData.setMobileNumber(mobileNo.getText().toString());
        CaptureDetails.farmerData.setEmail(emailId.getText().toString());
        CaptureDetails.farmerData.setKissanCardNumber(kissanCardNo.getText().toString());
        CaptureDetails.farmerData.setAadharNumber(aadharCardNo.getText().toString());
        CaptureDetails.farmerData.setBankName(bankName.getText().toString());
        CaptureDetails.farmerData.setBankBranch(branchName.getText().toString());
        CaptureDetails.farmerData.setAccountNumber(bankAccountNo.getText().toString());
        CaptureDetails.farmerData.setAccountHolderName(accountHolderName.getText().toString());

        if(male.isChecked())
            CaptureDetails.farmerData.setSex("M");
        else
            CaptureDetails.farmerData.setSex("F");

    }
}
