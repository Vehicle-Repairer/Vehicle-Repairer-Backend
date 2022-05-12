package shuhuai.vehiclerepairer.entity;

import lombok.*;
import shuhuai.vehiclerepairer.type.Sex;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Repairman extends BaseMan implements Serializable {
    private String profession;
    private BigDecimal hourCost;

    public Repairman(String id, String hashedPassword) {
        super(id, hashedPassword);
    }

    public Repairman(String id, String manName, String hashedPassword, Sex sex, String phone, Date birthday, String address, String emailAddress,
                     Timestamp createdTime, String profession, BigDecimal hourCost) {
        super(id, manName, hashedPassword, sex, phone, birthday, address, emailAddress, createdTime);
        this.profession = profession;
        this.hourCost = hourCost;
    }
}