package shuhuai.vehiclerepairer.mapper;

import org.apache.ibatis.annotations.Mapper;
import shuhuai.vehiclerepairer.entity.Salesman;

@Mapper
public interface SalesmanMapper {
    Integer insertSalesman(Salesman salesman);

    Integer insertSalesmanSelective(Salesman salesman);

    Integer updateSalesmanSelectiveById(Salesman salesman);

    Salesman selectSalesmanById(String id);
}