package com.infosys.hackathon.smartfert.data;

/**
 * Class name: SoilFertilityData
 * Description: Bean class for capturing the soil fertility details
 */
public class SoilFertilityData {

    private FarmerData farmerDetails;

    private LandData landDetails;

    private SoilData soilDetails;

    public FarmerData getFarmerDetails() {
        return farmerDetails;
    }

    public void setFarmerDetails(FarmerData farmerDetails) {
        this.farmerDetails = farmerDetails;
    }

    public LandData getLandDetails() {
        return landDetails;
    }

    public void setLandDetails(LandData landDetails) {
        this.landDetails = landDetails;
    }

    public SoilData getSoilDetails() {
        return soilDetails;
    }

    public void setSoilDetails(SoilData soilDetails) {
        this.soilDetails = soilDetails;
    }
}
