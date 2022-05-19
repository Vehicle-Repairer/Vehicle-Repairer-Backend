package shuhuai.vehiclerepairer.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@ToString
@NoArgsConstructor
public class RepairItem implements Serializable {
    private Integer itemId;
    private String itemName;
    private Integer needTime;
    private String profession;

    public RepairItem(String itemName, Integer needTime, String profession) {
        this.itemName = itemName;
        this.needTime = needTime;
        this.profession = profession;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getNeedTime() {
        return needTime;
    }

    public void setNeedTime(Integer needTime) {
        this.needTime = needTime;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
