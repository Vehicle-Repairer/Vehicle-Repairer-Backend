package shuhuai.vehiclerepairer.service;

public interface CustomerService {
    Integer addCustomer(String customerName, String customerType, Double discountRate, String contactPerson, String phone);
}
