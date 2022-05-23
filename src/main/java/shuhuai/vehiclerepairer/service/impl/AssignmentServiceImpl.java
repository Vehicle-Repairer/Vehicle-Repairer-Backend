package shuhuai.vehiclerepairer.service.impl;

import org.springframework.stereotype.Service;
import shuhuai.vehiclerepairer.entity.Assignment;
import shuhuai.vehiclerepairer.entity.Attorney;
import shuhuai.vehiclerepairer.entity.RepairItem;
import shuhuai.vehiclerepairer.entity.Repairman;
import shuhuai.vehiclerepairer.mapper.AssignmentMapper;
import shuhuai.vehiclerepairer.mapper.AttorneyMapper;
import shuhuai.vehiclerepairer.mapper.RepairItemMapper;
import shuhuai.vehiclerepairer.mapper.RepairmanMapper;
import shuhuai.vehiclerepairer.service.AssignmentService;
import shuhuai.vehiclerepairer.service.excep.common.ParamsException;
import shuhuai.vehiclerepairer.service.excep.common.ServerException;
import shuhuai.vehiclerepairer.type.Role;
import shuhuai.vehiclerepairer.utils.TokenValidator;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    @Resource
    private AssignmentMapper assignmentMapper;
    @Resource
    private AttorneyMapper attorneyMapper;
    @Resource
    private RepairItemMapper repairItemMapper;
    @Resource
    private RepairmanMapper repairmanMapper;

    @Override
    public Integer addAssignment(Integer attorneyId,Integer itemId,Boolean isFinished,String repairmanId) {
        if(attorneyId == null || itemId == null || isFinished == null || repairmanId == null) {
            throw new ParamsException("参数缺少");
        }
        if(attorneyMapper.selectAttorneyById(attorneyId) == null) {
            throw new ParamsException("委托书编号错误");
        }
        if(repairItemMapper.selectItemById(itemId) == null) {
            throw new ParamsException("维修项目编号错误");
        }
        if(repairmanMapper.selectRepairmanById(repairmanId) == null) {
            throw new ParamsException("维修员编号错误");
        }
        RepairItem repairItem = repairItemMapper.selectItemById(itemId);
        Repairman repairman = repairmanMapper.selectRepairmanById(repairmanId);
        if(!Objects.equals(repairItem.getProfession(), repairman.getProfession())) {
            throw new ParamsException("维修员专业与维修项目不符");
        }
        List<Assignment> assignments = assignmentMapper.selectAssignmentByAttorneyId(attorneyId);
        for(Assignment assignment : assignments) {
            if(Objects.equals(assignment.getItemId(), itemId)) {
                throw new ParamsException("该项目已存在");
            }
        }
        TokenValidator.checkRole(Role.业务员);
        Integer result;
        Assignment assignment = new Assignment(attorneyId, itemId, repairmanId,isFinished);
        result = 1;
        try {
                result = assignmentMapper.insertAssignmentSelective(assignment);
        }catch (Exception error) {
            throw new ServerException("添加失败");
        }
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
        Integer result = 1;
        try {
            result = assignmentMapper.updateAssignmentByAssignmentId(assignment);
        }catch (Exception error) {
            error.printStackTrace();
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
