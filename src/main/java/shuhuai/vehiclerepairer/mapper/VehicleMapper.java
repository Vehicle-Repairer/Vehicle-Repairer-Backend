package shuhuai.vehiclerepairer.mapper;

import shuhuai.vehiclerepairer.entity.Vehicle;

public interface VehicleMapper {
    Integer insertVehicleSelective(Vehicle vehicle);

    Integer updateVehicleSelectiveByFrameNumber(Vehicle vehicle);

    Vehicle selectVehicleByFrameNumber(String frameNumber);
}