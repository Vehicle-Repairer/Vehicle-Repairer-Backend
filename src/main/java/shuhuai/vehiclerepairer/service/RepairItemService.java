package shuhuai.vehiclerepairer.service;

import shuhuai.vehiclerepairer.entity.RepairItem;

import java.util.List;

public interface RepairItemService {
    void addRepairItem(String itemName, Integer needTime, String profession);

    void updateRepairItem(RepairItem repairItem);

    List<RepairItem> getAllRepairItems();

    List<RepairItem> getRepairItemsByProfession(String profession);

    List<RepairItem> getRepairItemsByItemName(String itemName);

    List<RepairItem> getRepairItemsByProAndName(String profession, String itemName);
}
