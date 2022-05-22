package shuhuai.vehiclerepairer.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shuhuai.vehiclerepairer.service.excep.BaseException;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class AttorneyServiceTests {

    @Resource
    AttorneyService attorneyService;

    @Test
    public void testAddAttorney() {
        Integer attorneyId;
        Date now = new Date();
        try {
            attorneyId = attorneyService.addAttorney(1, "ABCDEF", "123456", "加急", "小修",
                    2000, "3/4", "19120210", "凌佳伟", false, "车头损坏",
                    now, "自付");
            log.info("添加维修委托书成功" + attorneyId.toString() + "。");
        } catch (BaseException error) {
            log.error(error.getMessage());
        }
    }

    @Test
    public void testGetAttorneyByCustomerId() {
        try {
            attorneyService.getAttorneyByCustomerId(1);
            log.info("查询客户维修委托书成功：" + attorneyService.getAttorneyByCustomerId(1).toString());
        } catch (BaseException error) {
            log.error(error.getMessage());
        }
    }

    @Test
    public void testGetAttorneyBySalesmanId() {
        try {
            attorneyService.getAttorneyBySalesmanId("19120210");
            log.info("查询业务员的维修委托书：" + attorneyService.getAttorneyBySalesmanId("19120210").toString());
        } catch (BaseException error) {
            log.error(error.getMessage());
        }
    }
}

