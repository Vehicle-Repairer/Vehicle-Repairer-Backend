package shuhuai.vehiclerepairer.mapper;

import org.apache.ibatis.annotations.Mapper;
import shuhuai.vehiclerepairer.entity.Repairman;

@Mapper
public interface RepairmanMapper {
    int insertRepairman(Repairman repairman);

    int insertRepairmanSelective(Repairman repairman);

    int updateRepairmanSelectiveById(Repairman repairman);

    Repairman selectRepairmanById(String id);
}