package shuhuai.vehiclerepairer.mapper;

import org.apache.ibatis.annotations.Mapper;
import shuhuai.vehiclerepairer.entity.Repairman;

@Mapper
public interface RepairmanMapper {
    Integer insertRepairmanSelective(Repairman repairman);

    Integer updateRepairmanSelectiveById(Repairman repairman);

    Repairman selectRepairmanById(String id);

    Repairman selectRepairmanByPro(String profession);
}