package shuhuai.vehiclerepairer.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shuhuai.vehiclerepairer.service.excep.BaseException;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerServiceTests {
    @Resource
    CustomerService customerService;

    @Test
    public void testAddCustomer() {
        Integer customerId;
        try {
            customerId = customerService.addCustomer("杨浩东公司", "单位", 95., "杨浩东", "82638779");
            log.info("登记客户信息成功" + customerId.toString() + "。");
        } catch (BaseException error) {
            log.error(error.getMessage());
        }
    }

    @Test
    public void testGetCustomer() {
        try {
            customerService.getCustomer(3);
            log.info("获取客户信息成功：" + customerService.getCustomer(3).toString());
        } catch (BaseException error) {
            log.error(error.getMessage());
        }
    }
}