package shuhuai.vehiclerepairer.service.impl;


import org.springframework.stereotype.Service;
import shuhuai.vehiclerepairer.entity.Assignment;
import shuhuai.vehiclerepairer.entity.Consumption;
import shuhuai.vehiclerepairer.entity.ConsumptionShow;
import shuhuai.vehiclerepairer.entity.Parts;
import shuhuai.vehiclerepairer.mapper.AssignmentMapper;
import shuhuai.vehiclerepairer.mapper.PartConsumptionMapper;
import shuhuai.vehiclerepairer.mapper.PartsMapper;
import shuhuai.vehiclerepairer.service.ConsumptionService;
import shuhuai.vehiclerepairer.service.excep.common.ServerException;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ConsumptionServiceImpl implements ConsumptionService {
    @Resource
    private PartConsumptionMapper partConsumptionMapper;
    @Resource
    private AssignmentMapper assignmentMapper;
    @Resource
    private PartsMapper partsMapper;

    @Override
    public void addConsumption(Integer assignmentId, Integer partId, Integer partAmount) {
        if(assignmentId == null || partId == null || partAmount == null){
            throw new ServerException("参数缺少");
        }
        Integer result = 1;
        try {
            Consumption consumption = new Consumption(assignmentId, partId, partAmount);
            Assignment assignment = assignmentMapper.selectAssignmentById(assignmentId);
            if(assignment == null){
                throw new ServerException("派工单编号错误");
            }
            Parts parts = partsMapper.selectPartById(partId);
            if(parts == null){
                throw new ServerException("零件编号错误");
            }
            result = partConsumptionMapper.insertConsumptionSelective(consumption);
        } catch (Exception error) {
            throw new ServerException("添加失败");
        }
        if (result != 1) {
            throw new ServerException("服务器错误");
        }
    }

    @Override
    public void updateConsumption(Consumption consumption) {
        Integer result = 1;
        try {
            result = partConsumptionMapper.updateConsumptionById(consumption);
        } catch (Exception error) {
            error.printStackTrace();
        }
        if (result != 1) {
            throw new ServerException("服务器错误");
        }

    }

    @Override
    public List<Consumption> getConsumptionsByAssignmentId(Integer assignmentId) {
        if(assignmentId == null){
            throw new ServerException("参数缺少");
        }
        return partConsumptionMapper.selectConsumptionByAssignmentId(assignmentId);
    }

    @Override
    public BigDecimal getPartPrice(Integer assignmentId) {
        if(assignmentId == null){
            throw new ServerException("参数缺少");
        }
        return partConsumptionMapper.getAssignmentPrice(assignmentId);
    }

    @Override
    public BigDecimal getAttorneyPartPrice(Integer attorneyId) {
        if(attorneyId == null){
            throw new ServerException("参数缺少");
        }
        return partConsumptionMapper.getAttorneyPrice(attorneyId);
    }

    @Override
    public List<ConsumptionShow> getConsumptionShowByAssignmentId(Integer assignmentId) {
        if(assignmentId == null){
            throw new ServerException("参数缺少");
        }
        Assignment assignment = assignmentMapper.selectAssignmentById(assignmentId);
        if(assignment == null){
            throw new ServerException("派工单编号错误");
        }
        return partConsumptionMapper.getConsumptionShow(assignmentId);
    }
}
