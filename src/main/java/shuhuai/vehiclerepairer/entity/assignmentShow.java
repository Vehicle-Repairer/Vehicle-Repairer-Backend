package shuhuai.vehiclerepairer.entity;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class assignmentShow implements Serializable {
    private String repairItem;
    private Integer needTime;
    private String repairManName;
    private String profession;
    private String repairManPhone;
    private String status;

    public assignmentShow(String repairItem, Integer needTime, String repairManName, String profession, String repairManPhone) {
        this.repairItem = repairItem;
        this.needTime = needTime;
        this.repairManName = repairManName;
        this.profession = profession;
        this.repairManPhone = repairManPhone;
    }

    public String getRepairItem() {
        return repairItem;
    }

    public void setRepairItem(String repairItem) {
        this.repairItem = repairItem;
    }

    public Integer getNeedTime() {
        return needTime;
    }

    public void setNeedTime(Integer needTime) {
        this.needTime = needTime;
    }

    public String getRepairManName() {
        return repairManName;
    }

    public void setRepairManName(String repairManName) {
        this.repairManName = repairManName;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getRepairManPhone() {
        return repairManPhone;
    }

    public void setRepairManPhone(String repairManPhone) {
        this.repairManPhone = repairManPhone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
