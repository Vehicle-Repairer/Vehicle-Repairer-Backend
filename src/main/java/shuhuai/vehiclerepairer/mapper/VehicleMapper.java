package shuhuai.vehiclerepairer.mapper;

import org.apache.ibatis.annotations.Mapper;
import shuhuai.vehiclerepairer.entity.Vehicle;

import java.util.List;

@Mapper
public interface VehicleMapper {
    Integer insertVehicleSelective(Vehicle vehicle);

    Integer updateVehicleSelectiveByFrameNumber(Vehicle vehicle);

    Vehicle selectVehicleByFrameNumber(String frameNumber);

    List<Vehicle> selectVehicleByCustomerId(Integer customerId);
}