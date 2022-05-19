package shuhuai.vehiclerepairer.service;

import shuhuai.vehiclerepairer.entity.Customer;
import shuhuai.vehiclerepairer.entity.Repairman;

import java.util.List;

public interface RepairmanService {
    Repairman getRepairman(String repairmanId);

    List<Repairman> getRepairmanByPro(String profession);

    List<Repairman> selectAllRepairman();
}
