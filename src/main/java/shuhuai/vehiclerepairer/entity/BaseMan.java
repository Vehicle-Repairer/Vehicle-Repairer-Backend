package shuhuai.vehiclerepairer.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class BaseMan implements Serializable {
    private Integer id;
    private String account;
    private String manName;
    private String hashedPassword;
    private String sex;
    private String phone;
    private Date birthday;
    private String address;
    private String emailAddress;
    private Timestamp createdTime;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public BaseMan(String account, String hashPassword) {
        this.account = account;
        this.hashedPassword = hashPassword;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getManName() {
        return manName;
    }

    public void setManName(String manName) {
        this.manName = manName;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashPassword) {
        this.hashedPassword = hashPassword;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String toString() {
        return "BaseMan{" + "id=" + id + ", manName='" + manName + '\'' + ", hashedPassword='" + hashedPassword + '\'' + ", account='" + account + '\'' +
                ", phone='" + phone + '\'' + ", birthday=" + birthday + ", sex=" + sex + ", address='" + address + '\'' + ", emailAddress='" + emailAddress + '\'' + '}';
    }
}
