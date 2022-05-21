package shuhuai.vehiclerepairer.entity;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Assignment implements Serializable {
    private Integer assignmentId;
    private Integer attorneyId;
    private Integer itemId;
    private String repairmanId;
    private Boolean isFinished;

    public Assignment(Integer attorneyId, Integer itemId,String repairmanId,Boolean isFinished) {
        this.attorneyId = attorneyId;
        this.itemId = itemId;
        this.repairmanId = repairmanId;
        this.isFinished = isFinished;
    }

    public Integer getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Integer assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Integer getAttorneyId() {
        return attorneyId;
    }

    public void setAttorneyId(Integer attorneyId) {
        this.attorneyId = attorneyId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getRepairmanId() {
        return repairmanId;
    }

    public void setRepairmanId(String repairmanId) {
        this.repairmanId = repairmanId;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(Boolean finished) {
        isFinished = finished;
    }
}
