package shuhuai.vehiclerepairer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import shuhuai.vehiclerepairer.type.Sex;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Repairman extends BaseMan implements Serializable {
    private String profession;
    private BigDecimal hourCost;

    public Repairman(String account, String hashedPassword) {
        super(account, hashedPassword);
    }

    public Repairman(Integer id, String account, String manName, String hashedPassword, Sex sex, String phone, Date birthday, String address, String emailAddress,
                     Timestamp createdTime, String profession, BigDecimal hourCost) {
        super(id, account, manName, hashedPassword, sex, phone, birthday, address, emailAddress, createdTime);
        this.profession = profession;
        this.hourCost = hourCost;
    }

    public String toString() {
        return "Repairman{" + super.toString() + "profession='" + profession + '\'' + ", hourCost=" + '}';
    }
}