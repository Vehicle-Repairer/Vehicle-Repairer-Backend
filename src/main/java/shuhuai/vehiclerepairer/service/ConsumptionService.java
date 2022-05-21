package shuhuai.vehiclerepairer.service;

import shuhuai.vehiclerepairer.entity.Consumption;

import java.math.BigDecimal;
import java.util.List;

public interface ConsumptionService {
    void addConsumption(Integer assignmentId, Integer partId, Integer partAmount);

    void updateConsumption(Consumption consumption);

    List<Consumption> getConsumptionsByAssignmentId(Integer assignmentId);

    BigDecimal getPartPrice(Integer assignmentId);

    BigDecimal getAttorneyPartPrice(Integer attorneyId);
}
