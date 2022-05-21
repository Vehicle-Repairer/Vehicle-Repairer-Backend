package shuhuai.vehiclerepairer.service.impl;

import org.springframework.stereotype.Service;
import shuhuai.vehiclerepairer.entity.Attorney;
import shuhuai.vehiclerepairer.entity.RepairItem;
import shuhuai.vehiclerepairer.mapper.AttorneyMapper;
import shuhuai.vehiclerepairer.service.AttorneyService;
import shuhuai.vehiclerepairer.service.excep.common.ParamsException;
import shuhuai.vehiclerepairer.service.excep.common.ServerException;
import shuhuai.vehiclerepairer.type.Role;
import shuhuai.vehiclerepairer.utils.TokenValidator;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class AttorneyServiceImpl implements AttorneyService {
    @Resource
    private AttorneyMapper attorneyMapper;

    @Override
    public Integer addAttorney(Integer customerId, String frameNumber, String licenseNumber, String repairType, String repairAmount,
                               Integer range, String fuelAmount, String salesmanId, String manName, Boolean isFinished, String detailedFault,
                               Date inFactoryTime, Double finalPrice) {
        TokenValidator.checkRole(Role.业务员);
        if (licenseNumber == null || repairType == null || repairAmount == null || range == 0 || fuelAmount == null ||
                manName == null || detailedFault == null || inFactoryTime == null) {
            throw new ParamsException("参数错误");
        }
        if (!repairType.equals("普通") && !repairType.equals("加急")) {
            throw new ParamsException("参数错误");
        }
        if (!repairAmount.equals("小修") && !repairAmount.equals("中修") && !repairAmount.equals("大修")) {
            throw new ParamsException("参数错误");
        }
        finalPrice = 0.0;
        Attorney attorney = new Attorney(customerId, frameNumber, licenseNumber, repairType, repairAmount, range, fuelAmount, salesmanId, manName, isFinished, detailedFault,
                inFactoryTime, finalPrice);
        Integer result = attorneyMapper.insertAttorneySelective(attorney);
        if (result != 1) {
            throw new ServerException("服务器错误");
        }
        return attorney.getAttorneyId();
    }

    @Override
    public List<Attorney> getAttorneyByCustomerId(Integer customerId) {
        TokenValidator.checkRole(Role.业务员);
        if (customerId == null) {
            throw new ParamsException("参数错误");
        }
        return attorneyMapper.selectAttorneyByCustomerId(customerId);
    }

    @Override
    public List<Attorney> getAttorneyBySalesmanId(String salesmanId) {
        TokenValidator.checkRole(Role.业务员);
        if (salesmanId == null) {
            throw new ParamsException("参数错误");
        }
        return attorneyMapper.selectAttorneyBySalesmanId(salesmanId);
    }

    @Override
    public void updateAttorney(Attorney attorney) {
        Integer result;
        try {
            result = attorneyMapper.updateAttorneyByAttorneyId(attorney);
        }catch (Exception error) {
            throw new ServerException("服务器错误");
        }
        if (result != 1) {
            throw new ServerException("服务器错误");
        }
    }

    @Override
    public Attorney selectAttorneyById(Integer id) {
        Attorney attorney;
        try {
            attorney = attorneyMapper.selectAttorneyById(id);
        } catch (Exception error) {
            throw new ServerException("服务器错误");
        }
        return attorney;
    }
}