package shuhuai.vehiclerepairer.mapper;

import org.apache.ibatis.annotations.Mapper;
import shuhuai.vehiclerepairer.entity.Attorney;

import java.util.List;

@Mapper
public interface AttorneyMapper {
    Integer insertAttorneySelective(Attorney attorney);

    Integer updateAttorneyByAttorneyId(Attorney attorney);

    List<Attorney> selectAttorneyByCustomerId(Integer customerId);

    List<Attorney> selectAttorneyBySalesmanId(String salesmanId, Boolean isFinished);

    Attorney selectAttorneyById(Integer attorneyId);

}
