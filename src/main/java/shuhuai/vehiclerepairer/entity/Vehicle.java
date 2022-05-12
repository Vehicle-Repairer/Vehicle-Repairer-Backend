package shuhuai.vehiclerepairer.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Vehicle implements Serializable {
    private String frameNumber;
    private String licensNumber;
    private Integer customerId;
    private String color;
    private String vehicleModel;
    private String vehicleType;

    public String getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(String frameNumber) {
        this.frameNumber = frameNumber;
    }

    public String getLicensNumber() {
        return licensNumber;
    }

    public void setLicensNumber(String licensNumber) {
        this.licensNumber = licensNumber;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}