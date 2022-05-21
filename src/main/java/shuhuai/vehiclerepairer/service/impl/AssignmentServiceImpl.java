package shuhuai.vehiclerepairer.service.impl;

import org.springframework.stereotype.Service;
import shuhuai.vehiclerepairer.entity.Assignment;
import shuhuai.vehiclerepairer.mapper.AssignmentMapper;
import shuhuai.vehiclerepairer.service.AssignmentService;
import shuhuai.vehiclerepairer.service.excep.common.ParamsException;
import shuhuai.vehiclerepairer.service.excep.common.ServerException;
import shuhuai.vehiclerepairer.type.Role;
import shuhuai.vehiclerepairer.utils.TokenValidator;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    @Resource
    private AssignmentMapper assignmentMapper;

    @Override
    public Integer addAssignment(Integer attorneyId,Integer itemId,Boolean isFinished,String repairmanId) {
        TokenValidator.checkRole(Role.业务员);
        Integer result;
        Assignment assignment = new Assignment(attorneyId, itemId, repairmanId,isFinished);
        result = assignmentMapper.insertAssignmentSelective(assignment);

        if (result != 1) {
            throw new ServerException("服务器错误");
        }
        return assignment.getAttorneyId();
    }

    @Override
    public List<Assignment> getAssignmentByAttorneyId(Integer attorneyId) {
        TokenValidator.checkRole(Role.业务员);
        if (attorneyId == null) {
            throw new ParamsException("参数错误");
        }
        return assignmentMapper.selectAssignmentByAttorneyId(attorneyId);
    }

    @Override
    public List<Assignment> getAssignmentByRepairman(String repairmanId) {
        TokenValidator.checkRole(Role.维修员);
        if (repairmanId == null) {
            throw new ParamsException("参数错误");
        }
        return assignmentMapper.selectAssignmentByRepairMan(repairmanId);
    }

    @Override
    public void updateAssignment(Assignment assignment) {
        Integer result;
        try {
            result = assignmentMapper.updateAssignmentByAssignmentId(assignment);
        }catch (Exception error) {
            throw new ServerException("服务器错误");
        }
        if (result != 1) {
            throw new ServerException("服务器错误");
        }
    }

    @Override
    public BigDecimal attorneyRepairmanPrice(Integer attorneyId) {
        return assignmentMapper.attorneyRepairmanPrice(attorneyId);
    }

}
