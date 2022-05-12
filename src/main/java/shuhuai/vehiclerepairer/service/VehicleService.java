package shuhuai.vehiclerepairer.service;

import shuhuai.vehiclerepairer.entity.Vehicle;

import java.util.List;

public interface VehicleService {
    void addVehicle(String frameNumber, String licenseNumber, Integer customerId, String color, String vehicleModel, String vehicleType);

    List<Vehicle> getVehicleByCustomerId(Integer customerId);
}