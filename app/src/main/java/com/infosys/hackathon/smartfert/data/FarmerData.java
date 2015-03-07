package com.infosys.hackathon.smartfert.data;

/**
 * Class name: FarmerData
 * Description: Bean class for capturing the farmer data
 */
public class FarmerData {

    private String farmerId;

    private String farmerName;

    private byte[] farmerPhoto;

    private String sex;

    private String fatherName;

    private String address;

    private String mobileNumber;

    private String email;

    private String kissanCardNumber;

    private String aadharNumber;

    private String bankName;

    private String bankBranch;

    private String accountNumber;

    private String accountHolderName;

    /** if account belongs to a different person, the relationship */
    private String accountRelationship;

    public String getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(String farmerId) {
        this.farmerId = farmerId;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public byte[] getFarmerPhoto() {
        return farmerPhoto;
    }

    public void setFarmerPhoto(byte[] farmerPhoto) {
        this.farmerPhoto = farmerPhoto;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKissanCardNumber() {
        return kissanCardNumber;
    }

    public void setKissanCardNumber(String kissanCardNumber) {
        this.kissanCardNumber = kissanCardNumber;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getAccountRelationship() {
        return accountRelationship;
    }

    public void setAccountRelationship(String accountRelationship) {
        this.accountRelationship = accountRelationship;
    }
}
