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
public class VehicleServiceTests {
    @Resource
    VehicleService vehicleService;

    @Test
    public void testAddCustomer() {
        try {
            vehicleService.addVehicle("123456", "120765", 3, "白色", "帕萨特", "微型车");
            log.info("记录客户车辆信息成功。");
        } catch (BaseException error) {
            log.error(error.getMessage());
        }
    }
}
