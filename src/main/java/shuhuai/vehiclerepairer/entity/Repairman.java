package shuhuai.vehiclerepairer.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class Repairman extends BaseMan implements Serializable {
    private String profession;
    private BigDecimal hourCost;

    public Repairman(String account, String hashedPassword) {
        super(account, hashedPassword);
    }

    public String toString() {
        return "Repairman{" + super.toString() + "profession='" + profession + '\'' + ", hourCost=" + hourCost + '}';
    }
}