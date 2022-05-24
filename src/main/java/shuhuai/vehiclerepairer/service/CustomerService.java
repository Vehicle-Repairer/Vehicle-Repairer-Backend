package shuhuai.vehiclerepairer.service;

import shuhuai.vehiclerepairer.entity.Customer;

import java.util.List;

public interface CustomerService {
    Integer addCustomer(String customerName, String customerType, Double discountRate, String contactPerson, String phone);

    Customer getCustomer(Integer customerId);

    List<Customer> getCustomer(String phone, String name, String type);

    List<Customer> selectAllCustomer();


}