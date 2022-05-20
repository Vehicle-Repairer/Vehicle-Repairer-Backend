package shuhuai.vehiclerepairer.entity;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Consumption implements Serializable {
    private Integer consumptionId;
    private Integer assignmentId;
    private Integer partId;
    private Integer partAmount;

    public Consumption(Integer assignmentId, Integer partId, Integer partAmount) {
        this.assignmentId = assignmentId;
        this.partId = partId;
        this.partAmount = partAmount;
    }

    public Integer getConsumptionId() {
        return consumptionId;
    }

    public void setConsumptionId(Integer consumptionId) {
        this.consumptionId = consumptionId;
    }

    public Integer getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Integer assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Integer getPartId() {
        return partId;
    }

    public void setPartId(Integer partId) {
        this.partId = partId;
    }

    public Integer getPartAmount() {
        return partAmount;
    }

    public void setPartAmount(Integer partAmount) {
        this.partAmount = partAmount;
    }
}
