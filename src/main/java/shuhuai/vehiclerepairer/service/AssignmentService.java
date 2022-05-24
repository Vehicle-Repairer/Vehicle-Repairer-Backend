package shuhuai.vehiclerepairer.service;

import shuhuai.vehiclerepairer.entity.Assignment;

import java.math.BigDecimal;
import java.util.List;

public interface AssignmentService {
    Integer addAssignment(Integer attorneyId,Integer itemId,Boolean isFinished,String repairmanId);

    List<Assignment> getAssignmentByAttorneyId(Integer attorneyId);

    List<Assignment> getAssignmentByRepairman(String repairmanId,Boolean isFinished);

    void updateAssignment(Assignment assignment);

    BigDecimal attorneyRepairmanPrice(Integer attorneyId);

    Assignment getAssignmentById(Integer assignmentId);
}
