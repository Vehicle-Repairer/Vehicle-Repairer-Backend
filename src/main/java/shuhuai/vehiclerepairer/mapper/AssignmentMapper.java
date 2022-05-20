package shuhuai.vehiclerepairer.mapper;

import org.apache.ibatis.annotations.Mapper;
import shuhuai.vehiclerepairer.entity.Assignment;
import shuhuai.vehiclerepairer.entity.Attorney;

import java.util.List;

@Mapper
public interface AssignmentMapper {
    Integer insertAssignmentSelective(Assignment assignment);

    Integer updateAssignmentByAssignmentId(Assignment assignment);

    List<Assignment> selectAssignmentByAttorneyId(Integer attorneyId);
}
