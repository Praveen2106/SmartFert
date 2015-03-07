package com.infosys.hackathon.smartfert.utils;

import android.content.Context;
import android.util.Log;

import com.infosys.hackathon.smartfert.data.FarmerData;
import com.infosys.hackathon.smartfert.data.LandData;
import com.infosys.hackathon.smartfert.data.SoilData;
import com.infosys.hackathon.smartfert.data.SoilFertilityData;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;

/**
 * Class name: HeaderUtil
 * Description: Util class for constructing the header data
 */
public class HeaderUtil {

    public static void toXML(SoilFertilityData soilFertilityData, Context appContext) {

        try {
            StringBuffer sb = new StringBuffer();
            sb.append("<SmartFert>");
            FarmerData farmerData = soilFertilityData.getFarmerDetails();
            if (farmerData != null) {
                sb.append("<FarmerData>");
                sb.append(getXMLNode("FarmerID", farmerData.getFarmerId()));
                sb.append(getXMLNode("FarmerName", farmerData.getFarmerName()));
                sb.append(getXMLNode("Photo", farmerData.getFarmerPhoto().toString()));
                sb.append(getXMLNode("Sex", farmerData.getSex()));
                sb.append(getXMLNode("FatherName", farmerData.getFatherName()));
                sb.append(getXMLNode("Address", farmerData.getAddress()));
                sb.append(getXMLNode("MobileNumber", farmerData.getMobileNumber()));
                sb.append(getXMLNode("Email", farmerData.getEmail()));
                sb.append(getXMLNode("KissanNumber", farmerData.getKissanCardNumber()));
                sb.append(getXMLNode("AadharNumber", farmerData.getAadharNumber()));
                sb.append(getXMLNode("BankName", farmerData.getBankName()));
                sb.append(getXMLNode("BankBranch", farmerData.getBankBranch()));
                sb.append(getXMLNode("AccountNumber", farmerData.getAccountNumber()));
                sb.append(getXMLNode("HolderName", farmerData.getAccountHolderName()));
                sb.append(getXMLNode("Relationship", farmerData.getAccountRelationship()));
                sb.append("</FarmerData>");
            }
            SoilData soilData = new SoilData();
            if (soilData != null) {
                sb.append("<SoilData>");
                sb.append(getXMLNode("SoilReportNumber", soilData.getSoilReportNumber()));
                sb.append(getXMLNode("SoilLabNumber", soilData.getSoilReportNumber()));
                sb.append(getXMLNode("FarmerName", soilData.getSoilReportNumber()));
                sb.append(getXMLNode("IrrigationType", soilData.getSoilReportNumber()));
                sb.append(getXMLNode("CurrentCrop", soilData.getSoilReportNumber()));
                sb.append(getXMLNode("ClimaticZone", soilData.getSoilReportNumber()));
                sb.append(getXMLNode("CropRotation", soilData.getSoilReportNumber()));
                sb.append(getXMLNode("SampleDate", soilData.getSoilReportNumber()));
                sb.append(getXMLNode("SoilTexture", soilData.getSoilReportNumber()));
                sb.append(getXMLNode("CCcontent", soilData.getSoilReportNumber()));
                sb.append(getXMLNode("SaltContent", soilData.getSoilReportNumber()));
                sb.append(getXMLNode("PHContent", soilData.getSoilReportNumber()));
                sb.append(getXMLNode("ReportSentDate", soilData.getSoilReportNumber()));
                sb.append(getXMLNode("AvblOC", soilData.getSoilReportNumber()));
                sb.append(getXMLNode("BlanketOC", soilData.getSoilReportNumber()));
                sb.append(getXMLNode("BlanketNitrogen", soilData.getSoilReportNumber()));
                sb.append(getXMLNode("AvblNitrogen", soilData.getSoilReportNumber()));
                sb.append(getXMLNode("AvblPotassium", soilData.getSoilReportNumber()));
                sb.append(getXMLNode("BlanketPotassium", soilData.getSoilReportNumber()));
                sb.append(getXMLNode("AvblPhosphate", soilData.getSoilReportNumber()));
                sb.append(getXMLNode("BlanketPhosphate", soilData.getSoilReportNumber()));
                sb.append("</SoilData>");
            }
            LandData landData = new LandData();
            if (landData != null) {
                sb.append("<LandData");
                sb.append(getXMLNode("SurveyName", landData.getSurveyName()));
                sb.append(getXMLNode("Ownership", landData.getOwnership()));
                sb.append(getXMLNode("LandArea", landData.getLandArea()+""));
                sb.append(getXMLNode("District", landData.getDistrict()));
                sb.append(getXMLNode("Zonal", landData.getZonal()));
                sb.append(getXMLNode("Village", landData.getVillage()));
                sb.append(getXMLNode("CurrentCrop", landData.getCurrentCrop()));
                sb.append(getXMLNode("PreviousCrop", landData.getPreviousCrop()));
                sb.append("</LandData>");
            }
            sb.append("</SmartFert>");

            FileOutputStream outputStream;

            try {
                outputStream = appContext.openFileOutput(soilData.getSoilReportNumber(), Context.MODE_PRIVATE);
                outputStream.write(sb.toString().getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            Log.e("HeaderUtil", e.toString());
        }

    }

    static String getXMLNode(String tag, String value) {
        return "<"+tag+">"+value+"</"+tag+">";
    }

    public static String getFileData(String fileName, Context appContext) {
        try {
            FileInputStream in = appContext.openFileInput(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            System.out.println("getFileData:"+sb.toString());

            return sb.toString();
        } catch (Exception e) {
            Log.e("", e.toString());
        }
        return "";
    }
}
