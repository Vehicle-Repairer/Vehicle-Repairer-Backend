package shuhuai.vehiclerepairer.service.impl;

import org.springframework.stereotype.Service;
import shuhuai.vehiclerepairer.entity.Attorney;
import shuhuai.vehiclerepairer.entity.Vehicle;
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
    public Integer addAttorney(Attorney attorney){
        TokenValidator.checkRole(Role.业务员);

        if (attorney.getLicenseNumber() == null || attorney.getRepairType() == null || attorney.getRepairAmount() == null || attorney.getRange() == 0 || attorney.getFuelAmount() == null ||
                attorney.getManName() == null || attorney.getDetailedFault() == null || attorney.getInFactoryTime() == null ) {
            throw new ParamsException("参数错误");
        }
        if (!attorney.getRepairType().equals("普通") && !attorney.getRepairType().equals("加急")) {
            throw new ParamsException("参数错误");
        }
        if (!attorney.getRepairAmount().equals("小修") && !attorney.getRepairAmount().equals("中修") && !attorney.getRepairAmount().equals("大修")) {
            throw new ParamsException("参数错误");
        }
        attorney.setFinished(false);
        attorney.setFinalPrice(0.0);

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
            throw new IllegalArgumentException("参数错误");
        }
        return attorneyMapper.selectAttorneyByCustomerId(customerId);
    }

    @Override
    public List<Attorney> getAttorneyBySalesmanId(String salesmanId) {
        TokenValidator.checkRole(Role.业务员);
        if (salesmanId == null) {
            throw new IllegalArgumentException("参数错误");
        }
        return attorneyMapper.selectAttorneyBySalesmanId(salesmanId);
    }



    }

