package shuhuai.vehiclerepairer.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Customer implements Serializable {
    private Integer customerId;
    private String customerName;
    private String customerType;
    private Double discountRate;
    private String contactPerson;
    private String phone;

    public Customer(String customerName, String customerType, Double discountRate, String contactPerson, String phone) {
        this.customerId = null;
        this.customerName = customerName;
        this.customerType = customerType;
        this.discountRate = discountRate;
        this.contactPerson = contactPerson;
        this.phone = phone;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public Double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Double discountRate) {
        this.discountRate = discountRate;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}