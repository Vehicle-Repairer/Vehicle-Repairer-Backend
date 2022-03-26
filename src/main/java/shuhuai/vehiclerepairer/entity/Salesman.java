package shuhuai.vehiclerepairer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import shuhuai.vehiclerepairer.type.Sex;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Salesman extends BaseMan implements Serializable {
    public Salesman(String account, String hashedPassword) {
        super(account, hashedPassword);
    }

    public Salesman(Integer id, String account, String manName, String hashedPassword, Sex sex, String phone, Date birthday, String address, String emailAddress,
                    Timestamp createdTime) {
        super(id, account, manName, hashedPassword, sex, phone, birthday, address, emailAddress, createdTime);
    }
}