package shuhuai.vehiclerepairer.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@ToString
@NoArgsConstructor
public class ConsumptionShow  implements Serializable {
    private  Integer consumptionId;
    private Integer assignmentId;
    private  String repairItem;
    private String partName;
    private Integer partAmount;

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

    public String getRepairItem() {
        return repairItem;
    }

    public void setRepairItem(String repairItem) {
        this.repairItem = repairItem;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public Integer getPartAmount() {
        return partAmount;
    }

    public void setPartAmount(Integer partAmount) {
        this.partAmount = partAmount;
    }
}
