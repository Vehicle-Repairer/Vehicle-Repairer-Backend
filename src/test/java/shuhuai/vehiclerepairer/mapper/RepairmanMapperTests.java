package shuhuai.vehiclerepairer.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shuhuai.vehiclerepairer.entity.Repairman;

import javax.annotation.Resource;
import java.util.Objects;

/*** 维修员持久层测试
 @author 殊怀丶
 @version 1.0
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class RepairmanMapperTests {
    @Resource
    RepairmanMapper repairmanMapper;

    @Test
    public void testInsertRepairman() {
        String account = "lvzhihe_123@qq.com";
        String hashedPassword = "hashedprwq0421";
        Repairman repairman = new Repairman(account, hashedPassword);
        int rows = repairmanMapper.insertRepairmanSelective(repairman);
        log.info("插入" + rows + "行维修员数据：" + repairman + "。");
    }

    @Test
    public void testSelectRepairmanById() {
        String account = "lvzhihe_123@qq.com";
        Repairman repairman = repairmanMapper.selectRepairmanByAccount(account);
        log.info("查询维修员" + account + "数据：" + Objects.requireNonNullElse(repairman, "没有查询到维修员数据"));
    }
}
