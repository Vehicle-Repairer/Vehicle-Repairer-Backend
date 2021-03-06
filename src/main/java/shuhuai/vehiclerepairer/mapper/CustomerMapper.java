package shuhuai.vehiclerepairer.mapper;

import org.apache.ibatis.annotations.Mapper;
import shuhuai.vehiclerepairer.entity.Customer;

import java.util.List;

@Mapper
public interface CustomerMapper {
    Integer insertCustomerSelective(Customer customer);

    Integer updateCustomerSelectiveByCustomerId(Customer customer);

    Customer selectCustomerByCustomerId(Integer customerId);

    List<Customer> selectCustomerByParam(String phone, String name, String type);

    List<Customer> selectAllCustomer();


}