package com.infosys.hackathon.smartfert.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

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
            SoilData soilData = soilFertilityData.getSoilDetails();
            if (soilData != null) {
                sb.append("<SoilData>");
                sb.append(getXMLNode("SoilReportNumber", soilData.getSoilReportNumber()));
                sb.append(getXMLNode("SoilLabNumber", soilData.getSoilLabNumber()));
                sb.append(getXMLNode("FarmerName", soilData.getFarmerName()));
                sb.append(getXMLNode("IrrigationType", soilData.getIrrigationType()));
                sb.append(getXMLNode("CurrentCrop", soilData.getCurrentCrop()));
                sb.append(getXMLNode("ClimaticZone", soilData.getClimaticZone()));
                sb.append(getXMLNode("CropRotation", soilData.getCurrentCrop()));
                sb.append(getXMLNode("SampleDate", soilData.getSoilSampleDate()));
                sb.append(getXMLNode("SoilTexture", soilData.getSoilTexture()));
                sb.append(getXMLNode("CCcontent", soilData.getCalciumCarbonateContent()));
                sb.append(getXMLNode("SaltContent", soilData.getSaltContent()));
                sb.append(getXMLNode("PHContent", soilData.getpHContent()));
                sb.append(getXMLNode("ReportSentDate", soilData.getSoilReportSentDate()));
                sb.append(getXMLNode("AvblOC", String.valueOf(soilData.getAvailableOrganicContent())));
                sb.append(getXMLNode("BlanketOC", String.valueOf(soilData.getBlanketOrganicContent())));
                sb.append(getXMLNode("BlanketNitrogen", String.valueOf(soilData.getBlanketNitrogenContent())));
                sb.append(getXMLNode("AvblNitrogen", String.valueOf(soilData.getAvailableNitrogenContent())));
                sb.append(getXMLNode("AvblPotassium", String.valueOf(soilData.getAvailablePotassiumContent())));
                sb.append(getXMLNode("BlanketPotassium", String.valueOf(soilData.getBlanketPotassiumContent())));
                sb.append(getXMLNode("AvblPhosphate", String.valueOf(soilData.getAvailablePhosphateContent())));
                sb.append(getXMLNode("BlanketPhosphate", String.valueOf(soilData.getBlanketPhosphateContent())));
                sb.append("</SoilData>");
            }
            LandData landData = soilFertilityData.getLandDetails();
            if (landData != null) {
                sb.append("<LandData>");
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
                outputStream = appContext.openFileOutput(soilData.getSoilReportNumber().toLowerCase(), Context.MODE_PRIVATE);
                outputStream.write(sb.toString().getBytes());
                outputStream.close();
                Toast.makeText(appContext, "File Saved", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(appContext, "File Not Saved1", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        } catch (Exception e) {
            Toast.makeText(appContext, "File Saved2", Toast.LENGTH_SHORT).show();
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
