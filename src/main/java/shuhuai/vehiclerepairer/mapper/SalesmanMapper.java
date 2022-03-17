package shuhuai.vehiclerepairer.mapper;

import org.apache.ibatis.annotations.Mapper;
import shuhuai.vehiclerepairer.entity.Repairman;
import shuhuai.vehiclerepairer.entity.Salesman;

@Mapper
public interface SalesmanMapper {
    int insertSalesman(Salesman salesman);

    int insertSalesmanSelective(Salesman salesman);

    int deleteSalesmanById(int id);

    int updateSalesman(Salesman salesman);

    Salesman selectSalesmanByAccount(String account);
}