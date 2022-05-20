package shuhuai.vehiclerepairer.service;

import shuhuai.vehiclerepairer.entity.Assignment;

import java.util.List;

public interface AssignmentService {
    Integer addAssignment(Integer attorneyId,Integer itemId,Boolean isFinished);

    List<Assignment> getAssignmentByAttorneyId(Integer attorneyId);

}
