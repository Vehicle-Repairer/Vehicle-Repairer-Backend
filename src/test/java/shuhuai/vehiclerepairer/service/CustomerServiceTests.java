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
        try {
            customerService.addCustomer("吕陟赫", "个人", 10.1, "吕陟赫", "15221181692");
        } catch (BaseException error) {
            log.error("错误：" + error.getMessage());
        }
        log.info("登记客户信息成功。");
    }
}