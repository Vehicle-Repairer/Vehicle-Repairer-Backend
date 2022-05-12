package shuhuai.vehiclerepairer.service;

import shuhuai.vehiclerepairer.entity.Customer;

public interface CustomerService {
    Integer addCustomer(String customerName, String customerType, Double discountRate, String contactPerson, String phone);

    Customer getCustomer(Integer customerId);
}
