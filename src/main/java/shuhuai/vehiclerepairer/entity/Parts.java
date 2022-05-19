package shuhuai.vehiclerepairer.entity;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Parts implements Serializable {
    private Integer partId;
    private String partName;
    private BigDecimal partPrice;

    public Integer getPartId() {
        return partId;
    }

    public void setPartId(Integer partId) {
        this.partId = partId;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public BigDecimal getPartPrice() {
        return partPrice;
    }

    public void setPartPrice(BigDecimal partPrice) {
        this.partPrice = partPrice;
    }

    public Parts(String partName, BigDecimal partPrice) {
        this.partName = partName;
        this.partPrice = partPrice;
    }
}
