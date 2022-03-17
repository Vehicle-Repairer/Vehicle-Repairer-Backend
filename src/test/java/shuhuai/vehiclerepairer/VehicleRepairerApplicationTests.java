package shuhuai.vehiclerepairer;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

/*** 应用测试类
 @author 殊怀丶
 @version 1.0
 */
@SpringBootTest
@Slf4j
class VehicleRepairerApplicationTests {
    @Resource
    private DataSource dataSource;

    @Test
    void contextLoads() {
    }

    @Test
    void testDatabase() {
        try {
            dataSource.getConnection();
            log.info("连接到数据库成功。");
        } catch (SQLException error) {
            log.info("连接到数据库失败。");
            error.printStackTrace();
        }
    }
}