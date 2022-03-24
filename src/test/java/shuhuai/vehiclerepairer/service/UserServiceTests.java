package shuhuai.vehiclerepairer.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
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
        String account = "lvzhihe_123@qq.com";
        String password = "prwq0421";
        try {
            userService.repairmanActive(account, password);
        } catch (BaseException error) {
            log.error("错误：" + error.getMessage());
        }
        log.info("插入" + account + "维修员数据成功。");
    }
}
