package shuhuai.vehiclerepairer.service.impl;

import org.springframework.stereotype.Service;
import shuhuai.vehiclerepairer.entity.Repairman;
import shuhuai.vehiclerepairer.mapper.RepairmanMapper;
import shuhuai.vehiclerepairer.service.RepairmanService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RepairManServiceImpl implements RepairmanService {
    @Resource
    private RepairmanMapper repairmanMapper;

    @Override
    public Repairman getRepairman(String  repairmanId) {
        return repairmanMapper.selectRepairmanById(repairmanId);
    }

    @Override
    public List<Repairman> getRepairmanByPro(String profession) {
        return repairmanMapper.selectRepairmanByPro(profession);
    }

    @Override
    public List<Repairman> selectAllRepairman() {
        return repairmanMapper.selectAllRepairman();
    }
}
