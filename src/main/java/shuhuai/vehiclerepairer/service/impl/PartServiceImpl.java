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
        Integer result;
        try {
            Parts part = new Parts(partName,partPrice);
            result = partsMapper.insertPartSelective(part);
        } catch (Exception error) {
            throw new ServerException("服务器错误");
        }
        if (result != 1) {
            throw new ServerException("服务器错误");
        }
    }

    @Override
    public List<Parts> getAllParts() {
        return partsMapper.selectAllParts();
    }
}
