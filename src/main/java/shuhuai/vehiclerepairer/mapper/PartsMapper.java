package shuhuai.vehiclerepairer.mapper;

import org.apache.ibatis.annotations.Mapper;
import shuhuai.vehiclerepairer.entity.Parts;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface PartsMapper {
    Integer insertPartSelective(Parts part);
    List<Parts> selectAllParts();
}
