package com.infosys.hackathon.smartfert.http;

import android.content.Context;
import android.util.Log;

import com.infosys.hackathon.smartfert.data.FarmerData;
import com.infosys.hackathon.smartfert.data.LandData;
import com.infosys.hackathon.smartfert.data.SoilData;
import com.infosys.hackathon.smartfert.data.SoilFertilityData;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

/**
 * Class name: DataParser
 * Description: Class for parsing the xml data
 */
public class DataParser extends DefaultHandler {

    String root = "";

    FarmerData farmerDetails = new FarmerData();

    SoilData soilDetails = new SoilData();

    LandData landDetails = new LandData();

    SoilFertilityData fertilityData = new SoilFertilityData();

    private boolean farmerId;

    private boolean farmerPhoto;

    private boolean sex;

    private boolean fatherName;

    private boolean address;

    private boolean mobileNumber;

    private boolean email;

    private boolean kissanCardNumber;

    private boolean aadharNumber;

    private boolean bankName;

    private boolean bankBranch;

    private boolean accountNumber;

    private boolean accountHolderName;

    private boolean surveyName;

    private boolean ownership;

    private boolean landArea;

    private boolean district;

    private boolean zonal;

    private boolean village;


    private boolean cropDate;

    private boolean harvestDate;

    private boolean previousCrop;

    private boolean soilReportNumber;

    private boolean soilLabNumber;

    private boolean farmerName;

    private boolean irrigationType;

    private boolean currentCrop;

    private boolean climaticZone;

    private boolean cropRotation;

    private boolean soilSampleDate;

    private boolean soilTexture;

    private boolean calciumCarbonateContent;

    private boolean saltContent;

    private boolean pHContent;

    private boolean soilReportSentDate;

    private boolean availableOrganicContent;

    private boolean blanketOrganicContent;

    private boolean blanketNitrogenContent;

    private boolean availableNitrogenContent;

    private boolean blanketPotassiumContent;

    private boolean availablePotassiumContent;

    private boolean blanketPhosphateContent;

    private boolean availablePhosphateContent;

    /**
     * if account belongs to a different person, the relationship
     */
    private boolean accountRelationship;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("FarmerData")) {
            root = "FarmerData";
        } else if (qName.equalsIgnoreCase("LandData")) {
            root = "LandData";
        } else if (qName.equalsIgnoreCase("SoilData")) {
            root = "SoilData";
        }

        if (qName.equalsIgnoreCase("FarmerID")) {
            farmerId = true;
        } else if (qName.equalsIgnoreCase("FarmerName")) {
            farmerName = true;
        } else if (qName.equalsIgnoreCase("Photo")) {
            farmerPhoto = true;
        } else if (qName.equalsIgnoreCase("Sex")) {
            sex = true;
        } else if (qName.equalsIgnoreCase("FatherName")) {
            fatherName = true;
        } else if (qName.equalsIgnoreCase("Address")) {
            address = true;
        } else if (qName.equalsIgnoreCase("MobileNumber")) {
            mobileNumber = true;
        } else if (qName.equalsIgnoreCase("Email")) {
            email = true;
        } else if (qName.equalsIgnoreCase("KissanNumber")) {
            kissanCardNumber = true;
        } else if (qName.equalsIgnoreCase("AadharNumber")) {
            aadharNumber = true;
        } else if (qName.equalsIgnoreCase("BankName")) {
            bankName = true;
        } else if (qName.equalsIgnoreCase("BankBranch")) {
            bankBranch = true;
        } else if (qName.equalsIgnoreCase("AccountNumber")) {
            accountNumber = true;
        } else if (qName.equalsIgnoreCase("HolderName")) {
            accountHolderName = true;
        } else if (qName.equalsIgnoreCase("Relationship")) {
            accountRelationship = true;
        } else if (qName.equalsIgnoreCase("SurveyName")) {
            surveyName = true;
        } else if (qName.equalsIgnoreCase("Ownership")) {
            ownership = true;
        } else if (qName.equalsIgnoreCase("LandArea")) {
            landArea = true;
        } else if (qName.equalsIgnoreCase("District")) {
            district = true;
        } else if (qName.equalsIgnoreCase("Zonal")) {
            zonal = true;
        } else if (qName.equalsIgnoreCase("Village")) {
            village = true;
        } else if (qName.equalsIgnoreCase("CurrentCrop")) {
            currentCrop = true;
        } else if (qName.equalsIgnoreCase("PreviousCrop")) {
            previousCrop = true;
        } else if (qName.equalsIgnoreCase("SoilReportNumber")) {
            soilReportNumber = true;
        } else if (qName.equalsIgnoreCase("SoilLabNumber")) {
            soilLabNumber = true;
        } else if (qName.equalsIgnoreCase("FarmerName")) {
            farmerName = true;
        } else if (qName.equalsIgnoreCase("IrrigationType")) {
            irrigationType = true;
        } else if (qName.equalsIgnoreCase("CurrentCrop")) {
            currentCrop = true;
        } else if (qName.equalsIgnoreCase("ClimaticZone")) {
            climaticZone = true;
        } else if (qName.equalsIgnoreCase("CropRotation")) {
            cropRotation = true;
        } else if (qName.equalsIgnoreCase("SampleDate")) {
            soilSampleDate = true;
        } else if (qName.equalsIgnoreCase("SoilTexture")) {
            soilTexture = true;
        } else if (qName.equalsIgnoreCase("CCcontent")) {
            calciumCarbonateContent = true;
        } else if (qName.equalsIgnoreCase("SaltContent")) {
            saltContent = true;
        } else if (qName.equalsIgnoreCase("PHContent")) {
            pHContent = true;
        } else if (qName.equalsIgnoreCase("ReportSentDate")) {
            soilReportSentDate = true;
        } else if (qName.equalsIgnoreCase("AvblOC")) {
            availableOrganicContent = true;
        } else if (qName.equalsIgnoreCase("BlanketOC")) {
            blanketOrganicContent = true;
        } else if (qName.equalsIgnoreCase("AvblNitrogen")) {
            availableNitrogenContent = true;
        } else if (qName.equalsIgnoreCase("BlanketNitrogen")) {
            blanketNitrogenContent = true;
        } else if (qName.equalsIgnoreCase("AvblPotassium")) {
            availablePotassiumContent = true;
        } else if (qName.equalsIgnoreCase("BlanketPotassium")) {
            blanketPotassiumContent = true;
        } else if (qName.equalsIgnoreCase("AvblPhosphate")) {
            availablePhosphateContent = true;
        } else if (qName.equalsIgnoreCase("BlanketPhosphate")) {
            blanketPhosphateContent = true;
        }


    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("FarmerData")) {
            root = "";
        } else if (qName.equalsIgnoreCase("LandData")) {
            root = "";
        } else if (qName.equalsIgnoreCase("SoilData")) {
            root = "";
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        if (farmerId) {
            farmerDetails.setFarmerId(new String(ch, start, length));
            farmerId = false;
        } else if (farmerName) {
            System.out.println("Root:"+root+":"+new String(ch, start, length));
            if (root.equalsIgnoreCase("FarmerData")) {
                farmerDetails.setFarmerName(new String(ch, start, length));
            } else if (root.equalsIgnoreCase("SoilData")) {
                soilDetails.setFarmerName(new String(ch, start, length));
            }
            farmerName = false;
        } else if (farmerPhoto) {
            if (ch.length != 0)
                farmerDetails.setFarmerPhoto(new String(ch, start, length).getBytes());
            farmerPhoto = false;
        } else if (sex) {
            farmerDetails.setSex(new String(ch, start, length));
            sex = false;
        } else if (fatherName) {
            farmerDetails.setFatherName(new String(ch, start, length));
            fatherName = false;
        } else if (address) {
            farmerDetails.setAddress(new String(ch, start, length));
            address = false;
        } else if (mobileNumber) {
            farmerDetails.setMobileNumber(new String(ch, start, length));
            mobileNumber = false;
        } else if (email) {
            farmerDetails.setEmail(new String(ch, start, length));
            email = false;
        } else if (kissanCardNumber) {
            farmerDetails.setKissanCardNumber(new String(ch, start, length));
            kissanCardNumber = false;
        } else if (aadharNumber) {
            farmerDetails.setAadharNumber(new String(ch, start, length));
            aadharNumber = false;
        } else if (bankName) {
            farmerDetails.setBankName(new String(ch, start, length));
            bankName = false;
        } else if (bankBranch) {
            farmerDetails.setBankBranch(new String(ch, start, length));
            bankBranch = false;
        } else if (accountHolderName) {
            farmerDetails.setAccountHolderName(new String(ch, start, length));
            accountHolderName = false;
        } else if (accountNumber) {
            farmerDetails.setAccountNumber(new String(ch, start, length));
            accountNumber = false;
        } else if (accountRelationship) {
            farmerDetails.setAccountRelationship(new String(ch, start, length));
            accountRelationship = false;
        } else if (surveyName) {
            landDetails.setSurveyName(new String(ch, start, length));
            surveyName = false;
        } else if (ownership) {
            landDetails.setOwnership(new String(ch, start, length));
            ownership = false;
        } else if (landArea) {
            if (ch.length != 0 && !new String(ch, start, length).equalsIgnoreCase("NA")) {
                landDetails.setLandArea(Float.parseFloat(new String(ch, start, length)));
            }
            landArea = false;
        } else if (district) {
            landDetails.setDistrict(new String(ch, start, length));
            district = false;
        } else if (zonal) {
            landDetails.setZonal(new String(ch, start, length));
            zonal = false;
        } else if (village) {
            landDetails.setVillage(new String(ch, start, length));
            village = false;
        } else if (currentCrop) {
            if (root.equalsIgnoreCase("LandData")) {
                landDetails.setCurrentCrop(new String(ch, start, length));
            } else if (root.equalsIgnoreCase("SoilData")) {
                soilDetails.setCurrentCrop(new String(ch, start, length));
            }
            currentCrop = false;
        } else if (previousCrop) {
            landDetails.setPreviousCrop(new String(ch, start, length));
            previousCrop = false;
        } else if (soilReportNumber) {
            soilDetails.setSoilReportNumber(new String(ch, start, length));
            soilReportNumber = false;
        } else if (soilLabNumber) {
            soilDetails.setSoilLabNumber(new String(ch, start, length));
            soilLabNumber = false;
        } else if (irrigationType) {
            soilDetails.setIrrigationType(new String(ch, start, length));
            irrigationType = false;
        } else if (climaticZone) {
            soilDetails.setClimaticZone(new String(ch, start, length));
            climaticZone = false;
        } else if (availableOrganicContent) {
            if (ch.length != 0 && !new String(ch, start, length).equalsIgnoreCase("NA")) {
                soilDetails.setAvailableOrganicContent(Float.parseFloat(new String(ch, start, length)));
            }
            availableOrganicContent = false;
        } else if (blanketOrganicContent) {
            if (ch.length != 0 && !new String(ch, start, length).equalsIgnoreCase("NA")) {
                soilDetails.setBlanketOrganicContent(Float.parseFloat(new String(ch, start, length)));
            }
            blanketOrganicContent = false;
        } else if (availableNitrogenContent) {
            if (ch.length != 0 && !new String(ch, start, length).equalsIgnoreCase("NA")) {
                soilDetails.setAvailableNitrogenContent(Float.parseFloat(new String(ch, start, length)));
            }

            availableNitrogenContent = false;
        } else if (blanketNitrogenContent) {
            if (ch.length != 0 && !new String(ch, start, length).equalsIgnoreCase("NA")) {
                soilDetails.setBlanketNitrogenContent(Float.parseFloat(new String(ch, start, length)));
            }
            blanketNitrogenContent = false;
        } else if (availablePhosphateContent) {
            if (ch.length != 0 && !new String(ch, start, length).equalsIgnoreCase("NA")) {
                soilDetails.setAvailablePhosphateContent(Float.parseFloat(new String(ch, start, length)));
            }
            availablePhosphateContent = false;
        } else if (blanketPhosphateContent) {
            if (ch.length != 0 && !new String(ch, start, length).equalsIgnoreCase("NA")) {
                soilDetails.setBlanketPhosphateContent(Float.parseFloat(new String(ch, start, length)));
            }
            blanketPhosphateContent = false;
        } else if (availablePotassiumContent) {
            if (ch.length != 0 && !new String(ch, start, length).equalsIgnoreCase("NA")) {
                soilDetails.setAvailablePotassiumContent(Float.parseFloat(new String(ch, start, length)));
            }
            availablePotassiumContent = false;
        } else if (blanketPotassiumContent) {
            if (ch.length != 0 && !new String(ch, start, length).equalsIgnoreCase("NA")) {
                soilDetails.setBlanketPotassiumContent(Float.parseFloat(new String(ch, start, length)));
            }
            blanketPotassiumContent = false;
        } else if (cropRotation) {
            soilDetails.setCropRotation(new String(ch, start, length));
            cropRotation = false;
        } else if (soilReportSentDate) {
            soilDetails.setSoilReportSentDate(new String(ch, start, length));
            soilReportSentDate = false;
        } else if (calciumCarbonateContent) {
            soilDetails.setCalciumCarbonateContent(new String(ch, start, length));
            calciumCarbonateContent = false;
        } else if (pHContent) {
            soilDetails.setpHContent(new String(ch, start, length));
            pHContent = false;
        } else if (saltContent) {
            soilDetails.setSaltContent(new String(ch, start, length));
            saltContent = false;
        } else if (soilSampleDate) {
            soilDetails.setSoilSampleDate(new String(ch, start, length));
            soilSampleDate = false;
        } else if (soilTexture) {
            soilDetails.setSoilTexture(new String(ch, start, length));
            soilTexture = false;
        }
    }

    public SoilFertilityData getFertilityDetails() {
        SoilFertilityData soilFertilityData = new SoilFertilityData();
        System.out.println("::"+farmerDetails.getFarmerId());
        System.out.println("::"+farmerDetails.getFarmerName());
        soilFertilityData.setFarmerDetails(farmerDetails);
        soilFertilityData.setLandDetails(landDetails);
        soilFertilityData.setSoilDetails(soilDetails);

        return soilFertilityData;
    }

    public void parseXMLToFile(String fileName, String content, Context appContext) {
        try {

            FileOutputStream outputStream;

            try {
                outputStream = appContext.openFileOutput(fileName, Context.MODE_PRIVATE);
                outputStream.write(content.getBytes());
                outputStream.close();
                System.out.println("break");

                FileInputStream in = appContext.openFileInput(fileName);
                InputStreamReader inputStreamReader = new InputStreamReader(in);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }

                System.out.println("inside parse:"+sb.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            Log.e("DataParser", e.toString());
        }
    }
}
