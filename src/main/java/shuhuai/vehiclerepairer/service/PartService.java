package shuhuai.vehiclerepairer.service;

import shuhuai.vehiclerepairer.entity.Parts;

import java.math.BigDecimal;
import java.util.List;

public interface PartService {
    void addPart(String partName, BigDecimal partPrice);
    List<Parts> getAllParts();
}
