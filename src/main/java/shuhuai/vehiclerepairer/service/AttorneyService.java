package shuhuai.vehiclerepairer.service;

import com.zaxxer.hikari.util.ClockSource;
import shuhuai.vehiclerepairer.entity.Attorney;

import java.util.Date;
import java.util.List;

public interface AttorneyService {
    Integer addAttorney(Attorney attorney);

    List<Attorney> getAttorneyByCustomerId(Integer customerId);

    List<Attorney> getAttorneyBySalesmanId(String salesmanId);
}
