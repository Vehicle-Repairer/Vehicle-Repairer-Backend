package shuhuai.vehiclerepairer.service.impl;

import org.springframework.stereotype.Service;
import shuhuai.vehiclerepairer.entity.Attorney;
import shuhuai.vehiclerepairer.entity.Customer;
import shuhuai.vehiclerepairer.entity.RepairItem;
import shuhuai.vehiclerepairer.entity.Vehicle;
import shuhuai.vehiclerepairer.mapper.AttorneyMapper;
import shuhuai.vehiclerepairer.mapper.CustomerMapper;
import shuhuai.vehiclerepairer.mapper.VehicleMapper;
import shuhuai.vehiclerepairer.service.AttorneyService;
import shuhuai.vehiclerepairer.service.excep.common.ParamsException;
import shuhuai.vehiclerepairer.service.excep.common.ServerException;
import shuhuai.vehiclerepairer.type.Role;
import shuhuai.vehiclerepairer.utils.TokenValidator;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class AttorneyServiceImpl implements AttorneyService {
    @Resource
    private AttorneyMapper attorneyMapper;
    @Resource
    private CustomerMapper customerMapper;
    @Resource
    private VehicleMapper vehicleMapper;

    @Override
    public Integer addAttorney(Integer customerId, String frameNumber, String licenseNumber, String repairType, String repairAmount,
                               Integer range, String fuelAmount, String salesmanId, String manName, Boolean isFinished, String detailedFault,
                               Date inFactoryTime, String payType) {
        TokenValidator.checkRole(Role.业务员);
        if (customerId == null || licenseNumber == null || repairType == null || repairAmount == null || range == 0 || fuelAmount == null ||
                 detailedFault == null ) {
            throw new ParamsException("参数缺少");
        }
        if (!repairType.equals("普通") && !repairType.equals("加急")) {
            throw new ParamsException("参数错误");
        }
        if (!repairAmount.equals("小修") && !repairAmount.equals("中修") && !repairAmount.equals("大修")) {
            throw new ParamsException("参数错误");
        }
        Vehicle vechicle = vehicleMapper.selectVehicleByFrameNumber(frameNumber);
        if (vechicle == null) {
            throw new ServerException("车架号不存在");
        }
        Customer customer = customerMapper.selectCustomerByCustomerId(customerId);
        if (customer == null) {
            throw new ServerException("客户不存在");
        }
        if(!Objects.equals(vechicle.getCustomerId(), customerId)) {
            throw new ServerException("车架号不属于该客户");
        }
        Attorney attorney = new Attorney(customerId, frameNumber, licenseNumber, repairType, repairAmount, range, fuelAmount, salesmanId, manName, isFinished, detailedFault,
                inFactoryTime,payType);
        Integer result = 1;
        try {
            result = attorneyMapper.insertAttorneySelective(attorney);
        }catch (Exception error) {
            throw new ServerException("添加失败");
        }
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