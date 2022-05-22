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
public class AssignmentServiceTests {

    @Resource
    AssignmentService assignmentService;

    @Test
    public void testAddAssignment() {
        Integer assignmentId;
        try {
            assignmentId = assignmentService.addAssignment(1,2,false,"123");
            log.info("添加维修派工单成功" + assignmentId.toString() + "。");
        } catch (BaseException error) {
            log.error(error.getMessage());
        }
    }

    @Test
    public void testGetAssignmentByAttorneyId() {
        try {
            assignmentService.getAssignmentByAttorneyId(1);
            log.info("查询该委托书的维修派成功：" + assignmentService.getAssignmentByAttorneyId(1).toString());
        } catch (BaseException error) {
            log.error(error.getMessage());
        }
    }
}
