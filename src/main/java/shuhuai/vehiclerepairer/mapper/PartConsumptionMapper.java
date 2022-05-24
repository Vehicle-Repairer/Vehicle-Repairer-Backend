package shuhuai.vehiclerepairer.mapper;


import org.apache.ibatis.annotations.Mapper;
import shuhuai.vehiclerepairer.entity.Consumption;
import shuhuai.vehiclerepairer.entity.ConsumptionShow;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface PartConsumptionMapper {
    Integer insertConsumptionSelective(Consumption partConsumption);

    Integer updateConsumptionById(Consumption partConsumption);

    List<Consumption> selectConsumptionByAssignmentId(Integer assignmentId);

    BigDecimal getAssignmentPrice(Integer assignmentId);

    BigDecimal getAttorneyPrice(Integer attorneyId);

    List<ConsumptionShow> getConsumptionShow(Integer assignmentId);
}
