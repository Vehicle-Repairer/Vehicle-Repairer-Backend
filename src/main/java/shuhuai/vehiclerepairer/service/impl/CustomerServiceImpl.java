package shuhuai.vehiclerepairer.service.impl;

import org.springframework.stereotype.Service;
import shuhuai.vehiclerepairer.entity.Customer;
import shuhuai.vehiclerepairer.mapper.CustomerMapper;
import shuhuai.vehiclerepairer.service.CustomerService;
import shuhuai.vehiclerepairer.service.excep.common.ParamsException;
import shuhuai.vehiclerepairer.service.excep.common.ServerException;
import shuhuai.vehiclerepairer.type.Role;
import shuhuai.vehiclerepairer.utils.TokenValidator;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Resource
    private CustomerMapper customerMapper;

    @Override
    public Integer addCustomer(String customerName, String customerType, Double discountRate, String contactPerson, String phone) {
        TokenValidator.checkRole(Role.业务员);
        if (customerName == null || customerType == null || discountRate == 0 || contactPerson == null || phone == null) {
            throw new ParamsException("参数错误");
        }
        if (!customerType.equals("个人") && !customerType.equals("单位")) {
            throw new ParamsException("参数错误");
        }
        Customer customer = new Customer(customerName, customerType, discountRate, contactPerson, phone);
        Integer result =1;
        try {
                result = customerMapper.insertCustomerSelective(customer);
        }catch (Exception error) {
            throw new ServerException("添加失败");
        }
        if (result != 1) {
            throw new ServerException("服务器错误");
        }
        return customer.getCustomerId();
    }

    @Override
    public Customer getCustomer(Integer customerId) {
        TokenValidator.checkRole(Role.业务员);
        if (customerId == null) {
            throw new ParamsException("参数错误");
        }
        return customerMapper.selectCustomerByCustomerId(customerId);
    }

    @Override
    public List<Customer> getCustomer(String phone, String name, String type) {
        TokenValidator.checkRole(Role.业务员);
        if (phone == null && name == null && type == null) {
            return customerMapper.selectAllCustomer();
        }
        return customerMapper.selectCustomerByParam(phone, name, type);
    }

    @Override
    public List<Customer> selectAllCustomer() {
        return customerMapper.selectAllCustomer();
    }


}