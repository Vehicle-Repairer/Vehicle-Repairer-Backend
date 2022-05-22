package shuhuai.vehiclerepairer.service.impl;


import org.springframework.stereotype.Service;
import shuhuai.vehiclerepairer.entity.RepairItem;
import shuhuai.vehiclerepairer.mapper.RepairItemMapper;
import shuhuai.vehiclerepairer.service.RepairItemService;
import shuhuai.vehiclerepairer.service.excep.common.ServerException;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RepairItemServiceImpl implements RepairItemService {
    @Resource
    private RepairItemMapper repairItemMapper;

    @Override
    public void addRepairItem(String itemName, Integer needTime, String profession) {
        Integer result =1;
        try {
            RepairItem repairItem = new RepairItem(itemName, needTime, profession);
            result = repairItemMapper.insertRepairItemSelective(repairItem);
        } catch (Exception error) {
            error.printStackTrace();
        }
        if (result != 1) {
            throw new ServerException("服务器错误");
        }
    }

    @Override
    public void updateRepairItem(RepairItem repairItem) {
        Integer result = 1;
        try {
            result = repairItemMapper.updateRepairItemById(repairItem);
        } catch (Exception error) {
            error.printStackTrace();
        }
        if (result != 1) {
            throw new ServerException("服务器错误");
        }
    }

    @Override
    public RepairItem getRepairItem(Integer itemId) {
        return repairItemMapper.selectItemById(itemId);
    }

    @Override
    public List<RepairItem> getAllRepairItems() {
        return repairItemMapper.selectAllItems();
    }

    @Override
    public List<RepairItem> getRepairItemsByProfession(String profession) {
        return repairItemMapper.selectItemsByProfession(profession);
    }

    @Override
    public List<RepairItem> getRepairItemsByItemName(String itemName) {
        return repairItemMapper.selectItemsByItemName(itemName);
    }

    @Override
    public List<RepairItem> getRepairItemsByProAndName(String profession, String itemName) {
        return repairItemMapper.selectItemsByProfessionAndName(profession, itemName);
    }
}
