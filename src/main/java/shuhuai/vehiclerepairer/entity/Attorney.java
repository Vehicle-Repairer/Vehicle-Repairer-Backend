package shuhuai.vehiclerepairer.entity;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Attorney implements Serializable {
    private Integer attorneyId;
    private Integer customerId;
    private String frameNumber;
    private String licenseNumber;
    private String repairType;
    private String repairAmount;
    private Integer range;
    private String fuelAmount;
    private String salesmanId;
    private String manName;
    private Boolean isFinished;
    private String detailedFault;
    private Date inFactoryTime;
    private Double finalPrice;

    public Attorney(Integer customerId, String frameNumber, String licenseNumber, String repairType, String repairAmount,
                    Integer range, String fuelAmount, String salesmanId, String manName, Boolean isFinished, String detailedFault,
                    Date inFactoryTime, Double finalPrice) {
        this.customerId = customerId;
        this.frameNumber = frameNumber;
        this.licenseNumber = licenseNumber;
        this.repairType = repairType;
        this.repairAmount = repairAmount;
        this.range = range;
        this.fuelAmount = fuelAmount;
        this.salesmanId = salesmanId;
        this.manName = manName;
        this.isFinished = isFinished;
        this.detailedFault = detailedFault;
        this.inFactoryTime = inFactoryTime;
        this.finalPrice = finalPrice;


    }

    public Integer getAttorneyId() {
        return attorneyId;
    }

    public void setAttorneyId(Integer attorneyId) {
        this.attorneyId = attorneyId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(String frameNumber) {
        this.frameNumber = frameNumber;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getRepairType() {
        return repairType;
    }

    public void setRepairType(String repairType) {
        this.repairType = repairType;
    }

    public String getRepairAmount() {
        return repairAmount;
    }

    public void setRepairAmount(String repairAmount) {
        this.repairAmount = repairAmount;
    }

    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }

    public String getFuelAmount() {
        return fuelAmount;
    }

    public void setFuelAmount(String fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    public String getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(String salesmanId) {
        this.salesmanId = salesmanId;
    }

    public String getManName() {
        return manName;
    }

    public void setManName(String manName) {
        this.manName = manName;
    }

    public Boolean getFinished() {
        return isFinished;
    }

    public void setFinished(Boolean finished) {
        isFinished = finished;
    }

    public String getDetailedFault() {
        return detailedFault;
    }

    public void setDetailedFault(String detailedFault) {
        this.detailedFault = detailedFault;
    }

    public Date getInFactoryTime() {
        return inFactoryTime;
    }

    public void setInFactoryTime(Date inFactoryTime) {
        this.inFactoryTime = inFactoryTime;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }
}
