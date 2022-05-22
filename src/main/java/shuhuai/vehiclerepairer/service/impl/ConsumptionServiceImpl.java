package shuhuai.vehiclerepairer.service.impl;


import org.springframework.stereotype.Service;
import shuhuai.vehiclerepairer.entity.Consumption;
import shuhuai.vehiclerepairer.mapper.PartConsumptionMapper;
import shuhuai.vehiclerepairer.service.ConsumptionService;
import shuhuai.vehiclerepairer.service.excep.common.ServerException;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ConsumptionServiceImpl implements ConsumptionService {
    @Resource
    private PartConsumptionMapper partConsumptionMapper;

    @Override
    public void addConsumption(Integer assignmentId, Integer partId, Integer partAmount) {
        Integer result = 1;
        try {
            Consumption consumption = new Consumption(assignmentId, partId, partAmount);
            result = partConsumptionMapper.insertConsumptionSelective(consumption);
        } catch (Exception error) {
            error.printStackTrace();
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
        return partConsumptionMapper.selectConsumptionByAssignmentId(assignmentId);
    }

    @Override
    public BigDecimal getPartPrice(Integer assignmentId) {
        return partConsumptionMapper.getAssignmentPrice(assignmentId);
    }

    @Override
    public BigDecimal getAttorneyPartPrice(Integer attorneyId) {
        return partConsumptionMapper.getAttorneyPrice(attorneyId);
    }
}
