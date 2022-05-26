package shuhuai.vehiclerepairer.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shuhuai.vehiclerepairer.service.excep.BaseException;

import javax.annotation.Resource;

/*** 用户服务测试类
 @author 殊怀丶
 @version 1.0
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {
    @Resource
    UserService userService;

    @Test
    public void testRepairmanRegister() {
        String id = "19120176";
        String password = "prwq0421";
        try {
            userService.repairmanActive(id, password);
            log.info("插入" + id + "维修员数据成功。");
        } catch (BaseException error) {
            log.error(error.getMessage());
        }
    }
}
