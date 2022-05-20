package shuhuai.vehiclerepairer.service;

import shuhuai.vehiclerepairer.entity.Attorney;

import java.util.Date;
import java.util.List;

public interface AttorneyService {
    Integer addAttorney(Integer customerId, String frameNumber, String licenseNumber, String repairType, String repairAmount,
                        Integer range, String fuelAmount, String salesmanId, String manName, Boolean isFinished, String detailedFault,
                        Date inFactoryTime, Double finalPrice);


    List<Attorney> getAttorneyByCustomerId(Integer customerId);

    List<Attorney> getAttorneyBySalesmanId(String salesmanId);
}
