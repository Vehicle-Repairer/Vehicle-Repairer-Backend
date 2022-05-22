package shuhuai.vehiclerepairer.service.impl;

import org.springframework.stereotype.Service;
import shuhuai.vehiclerepairer.entity.Parts;
import shuhuai.vehiclerepairer.mapper.PartsMapper;
import shuhuai.vehiclerepairer.service.PartService;
import shuhuai.vehiclerepairer.service.excep.common.ServerException;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class PartServiceImpl implements PartService {
    @Resource
    PartsMapper partsMapper;

    @Override
    public void addPart(String partName, BigDecimal partPrice) {
        if(partName == null || partName.equals("")){
            throw new ServerException("请输入配件名称");
        }
        if(partPrice == null){
            throw new ServerException("请输入零件价格");
        }
        Integer result = 1;
        try {
            Parts part = new Parts(partName, partPrice);
            result = partsMapper.insertPartSelective(part);
        } catch (Exception error) {
            throw new ServerException("添加失败");
        }
        if (result != 1) {
            throw new ServerException("服务器错误");
        }
    }

    @Override
    public List<Parts> getAllParts() {
        return partsMapper.selectAllParts();
    }

    @Override
    public void updatePart(Parts part) {
        if(part == null){
            throw new ServerException("请输入配件信息");
        }
        Integer result;
        try {
            result = partsMapper.updatePartSelectiveById(part);
        } catch (Exception error) {
            throw new ServerException("服务器错误");
        }
        if (result != 1) {
            throw new ServerException("服务器错误");
        }
    }
}
