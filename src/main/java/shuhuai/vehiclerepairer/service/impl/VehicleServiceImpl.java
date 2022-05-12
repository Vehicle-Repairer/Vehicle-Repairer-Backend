package shuhuai.vehiclerepairer.service.impl;

import org.springframework.stereotype.Service;
import shuhuai.vehiclerepairer.entity.Vehicle;
import shuhuai.vehiclerepairer.mapper.VehicleMapper;
import shuhuai.vehiclerepairer.service.VehicleService;
import shuhuai.vehiclerepairer.service.excep.common.ServerException;
import shuhuai.vehiclerepairer.type.Role;
import shuhuai.vehiclerepairer.utils.TokenValidator;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Resource
    private VehicleMapper vehicleMapper;

    @Override
    public void addVehicle(String frameNumber, String licenseNumber, Integer customerId, String color, String vehicleModel, String vehicleType) {
        TokenValidator.checkRole(Role.业务员);
        if (frameNumber == null || licenseNumber == null || customerId == null || color == null || vehicleModel == null || vehicleType == null) {
            throw new IllegalArgumentException("参数错误");
        }
        Vehicle vehicle = new Vehicle(frameNumber, licenseNumber, customerId, color, vehicleModel, vehicleType);
        Integer result;
        try {
            result = vehicleMapper.insertVehicleSelective(vehicle);
        } catch (Exception error) {
            throw new ServerException("服务器错误");
        }
        if (result != 1) {
            throw new ServerException("服务器错误");
        }
    }

    @Override
    public List<Vehicle> getVehicleByCustomerId(Integer customerId) {
        TokenValidator.checkRole(Role.业务员);
        if (customerId == null) {
            throw new IllegalArgumentException("参数错误");
        }
        return vehicleMapper.selectVehicleByCustomerId(customerId);
    }
}