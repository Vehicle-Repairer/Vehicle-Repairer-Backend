package shuhuai.vehiclerepairer.service.impl;

import org.springframework.stereotype.Service;
import shuhuai.vehiclerepairer.entity.Repairman;
import shuhuai.vehiclerepairer.mapper.RepairmanMapper;
import shuhuai.vehiclerepairer.service.RepairmanService;
import shuhuai.vehiclerepairer.service.excep.common.ServerException;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RepairManServiceImpl implements RepairmanService {
    @Resource
    private RepairmanMapper repairmanMapper;

    @Override
    public Repairman getRepairman(String repairmanId) {
        if (repairmanId == null) {
            throw new ServerException("参数缺少");
        }
        return repairmanMapper.selectRepairmanById(repairmanId);
    }

    @Override
    public List<Repairman> getRepairmanByPro(String profession) {
        if (profession == null) {
            throw new ServerException("参数缺少");
        }
        return repairmanMapper.selectRepairmanByPro(profession);
    }

    @Override
    public List<Repairman> selectAllRepairman() {
        return repairmanMapper.selectAllRepairman();
    }
}
