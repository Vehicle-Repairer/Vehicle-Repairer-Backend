package shuhuai.vehiclerepairer.mapper;

import org.apache.ibatis.annotations.Mapper;
import shuhuai.vehiclerepairer.entity.Customer;

@Mapper
public interface CustomerMapper {
    Integer insertCustomerSelective(Customer customer);

    Integer updateCustomerSelectiveByCustomerId(Customer customer);

    Customer selectCustomerByCustomerId(Integer customerId);
}