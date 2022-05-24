package shuhuai.vehiclerepairer.mapper;

import org.apache.ibatis.annotations.Mapper;
import shuhuai.vehiclerepairer.entity.Assignment;
import shuhuai.vehiclerepairer.entity.Attorney;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface AssignmentMapper {
    Integer insertAssignmentSelective(Assignment assignment);

    Integer updateAssignmentByAssignmentId(Assignment assignment);

    List<Assignment> selectAssignmentByAttorneyId(Integer attorneyId);

    List<Assignment> selectAssignmentByRepairMan(String repairmanId, Boolean isFinished);

    BigDecimal attorneyRepairmanPrice(Integer attorneyId);

    Assignment selectAssignmentById(Integer assignmentId);
}
