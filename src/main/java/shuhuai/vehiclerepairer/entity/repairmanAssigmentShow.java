package shuhuai.vehiclerepairer.entity;


import lombok.AllArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class repairmanAssigmentShow {
    private Integer assignmentId;
    private String frameNumber;
    private String repairManName;
    private String repairItem;
    private String repairType;
    private String repairTime;
    private String detailedFault;
    private String isFinished;

    public Integer getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Integer assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(String frameNumber) {
        this.frameNumber = frameNumber;
    }

    public String getRepairManName() {
        return repairManName;
    }

    public void setRepairManName(String repairManName) {
        this.repairManName = repairManName;
    }

    public String getRepairItem() {
        return repairItem;
    }

    public void setRepairItem(String repairItem) {
        this.repairItem = repairItem;
    }

    public String getRepairType() {
        return repairType;
    }

    public void setRepairType(String repairType) {
        this.repairType = repairType;
    }

    public String getRepairTime() {
        return repairTime;
    }

    public void setRepairTime(String repairTime) {
        this.repairTime = repairTime;
    }

    public String getDetailedFault() {
        return detailedFault;
    }

    public void setDetailedFault(String detailedFault) {
        this.detailedFault = detailedFault;
    }

    public String getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(String isFinished) {
        this.isFinished = isFinished;
    }
}
