package shuhuai.vehiclerepairer.mapper;

import shuhuai.vehiclerepairer.entity.Attorney;

import java.util.List;

public interface AttorneyMapper {
    Integer insertAttorneySelective(Attorney attorney);

    Integer updateAttorneyByAttorneyId(Attorney attorney);

    List<Attorney> selectAttorneyByCustomerId(Integer customerId);

    List<Attorney> selectAttorneyBySalesmanId(String salesmanId);

}
