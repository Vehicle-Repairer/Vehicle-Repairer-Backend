package shuhuai.vehiclerepairer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import shuhuai.vehiclerepairer.type.Sex;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class Salesman extends BaseMan implements Serializable {
    public Salesman(String id, String hashedPassword) {
        super(id, hashedPassword);
    }

    public Salesman(String id, String manName, String hashedPassword, Sex sex, String phone, Date birthday, String address, String emailAddress,
                    Timestamp createdTime) {
        super(id, manName, hashedPassword, sex, phone, birthday, address, emailAddress, createdTime);
    }
}