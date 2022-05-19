package shuhuai.vehiclerepairer.mapper;

import org.apache.ibatis.annotations.Mapper;
import shuhuai.vehiclerepairer.entity.Repairman;

import java.util.List;

@Mapper
public interface RepairmanMapper {
    Integer insertRepairmanSelective(Repairman repairman);

    Integer updateRepairmanSelectiveById(Repairman repairman);

    Repairman selectRepairmanById(String id);

    List<Repairman> selectRepairmanByPro(String profession);

    List<Repairman> selectAllRepairman();
}