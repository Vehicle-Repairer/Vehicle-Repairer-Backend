package shuhuai.vehiclerepairer.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shuhuai.vehiclerepairer.entity.Repairman;
import shuhuai.vehiclerepairer.utils.Hashing;

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
        String id = "19120176";
        String hashedPassword = Hashing.getHashedString("prwq0421");
        Repairman repairman = new Repairman(id, hashedPassword, "程序员");
        int rows = repairmanMapper.insertRepairmanSelective(repairman);
        log.info("插入" + rows + "行维修员数据：" + repairman + "。");
    }

    @Test
    public void testSelectRepairmanById() {
        String id = "19120176";
        Repairman repairman = repairmanMapper.selectRepairmanById(id);
        log.info("查询维修员" + id + "数据：" + Objects.requireNonNullElse(repairman, "没有查询到维修员数据"));
    }
}