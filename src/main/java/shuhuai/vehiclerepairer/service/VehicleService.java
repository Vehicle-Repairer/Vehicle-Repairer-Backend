package shuhuai.vehiclerepairer.service;

public interface VehicleService {
    void addVehicle(String frameNumber, String licenseNumber, Integer customerId, String color, String vehicleModel, String vehicleType);
}
