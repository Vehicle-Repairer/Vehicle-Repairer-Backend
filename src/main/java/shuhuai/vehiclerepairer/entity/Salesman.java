package shuhuai.vehiclerepairer.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class Salesman extends BaseMan implements Serializable {
    public Salesman(String account, String hashedPassword) {
        super(account, hashedPassword);
    }
}