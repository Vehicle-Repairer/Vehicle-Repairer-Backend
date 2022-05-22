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
        if(itemName == null || needTime == null || profession == null){
            throw new ServerException("参数缺少");
        }
        Integer result;
        try {
            RepairItem repairItem = new RepairItem(itemName, needTime, profession);
            result = repairItemMapper.insertRepairItemSelective(repairItem);
        } catch (Exception error) {
            throw new ServerException("添加失败");
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
        if (itemId == null) {
            throw new ServerException("参数缺少");
        }
        return repairItemMapper.selectItemById(itemId);
    }

    @Override
    public List<RepairItem> getAllRepairItems() {
        return repairItemMapper.selectAllItems();
    }

    @Override
    public List<RepairItem> getRepairItemsByProfession(String profession) {
        if (profession == null) {
            throw new ServerException("参数缺少");
        }
        return repairItemMapper.selectItemsByProfession(profession);
    }

    @Override
    public List<RepairItem> getRepairItemsByItemName(String itemName) {
        if(itemName == null){
            throw new ServerException("参数缺少");
        }
        return repairItemMapper.selectItemsByItemName(itemName);
    }

    @Override
    public List<RepairItem> getRepairItemsByProAndName(String profession, String itemName) {
        if(profession == null || itemName == null){
            throw new ServerException("参数缺少");
        }
        return repairItemMapper.selectItemsByProfessionAndName(profession, itemName);
    }
}
