package shuhuai.vehiclerepairer.mapper;


import org.apache.ibatis.annotations.Mapper;
import shuhuai.vehiclerepairer.entity.RepairItem;

import java.util.List;

@Mapper
public interface RepairItemMapper {
    Integer insertRepairItemSelective(RepairItem repairItem);

    Integer updateRepairItemById(RepairItem repairItem);

    RepairItem selectItemById(Integer itemId);

    List<RepairItem> selectAllItems();

    List<RepairItem> selectItemsByItemName(String itemName);

    List<RepairItem> selectItemsByProfession(String profession);

    List<RepairItem> selectItemsByProfessionAndName(String profession, String itemName);
}
