package shuhuai.vehiclerepairer.service.impl;

import org.springframework.stereotype.Service;
import shuhuai.vehiclerepairer.entity.Repairman;
import shuhuai.vehiclerepairer.entity.Salesman;
import shuhuai.vehiclerepairer.mapper.RepairmanMapper;
import shuhuai.vehiclerepairer.mapper.SalesmanMapper;
import shuhuai.vehiclerepairer.service.UserService;
import shuhuai.vehiclerepairer.service.excep.common.ParamsException;
import shuhuai.vehiclerepairer.service.excep.common.ServerException;
import shuhuai.vehiclerepairer.service.excep.user.IdDuplicatedException;
import shuhuai.vehiclerepairer.service.excep.user.IdPasswordErrorException;
import shuhuai.vehiclerepairer.utils.Hashing;

import javax.annotation.Resource;

/*** 用户服务实现类
 @author 殊怀丶
 @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    RepairmanMapper repairmanMapper;
    @Resource
    SalesmanMapper salesmanMapper;

    @Override
    public void repairmanActive(String id, String password) throws IdDuplicatedException, ParamsException, ServerException {
        if (id == null || password == null) {
            throw new ParamsException("参数错误");
        }
        String hashedPassword = Hashing.getHashedString(password);
        if (repairmanMapper.selectRepairmanById(id) != null) {
            throw new IdDuplicatedException("账户已激活");
        }
        Repairman repairman = new Repairman(id, hashedPassword);
        Integer result = repairmanMapper.insertRepairmanSelective(repairman);
        if (result != 1) {
            throw new ServerException("服务器错误");
        }
    }

    @Override
    public void repairmanLogin(String id, String password) throws ParamsException, IdPasswordErrorException {
        if (id == null || password == null) {
            throw new ParamsException("参数错误");
        }
        Repairman result = repairmanMapper.selectRepairmanById(id);
        String hashedPassword = Hashing.getHashedString(password);
        if (result == null || !result.getHashedPassword().equals(hashedPassword)) {
            throw new IdPasswordErrorException("账户或密码错误");
        }
    }

    @Override
    public void repairmanModifyInformation(Repairman repairman) {
        Integer result = repairmanMapper.updateRepairmanSelectiveById(repairman);
        if (result != 1) {
            throw new ServerException("服务器错误");
        }
    }

    @Override
    public void salesmanActive(String id, String password) throws IdDuplicatedException, ParamsException, ServerException {
        if (id == null || password == null) {
            throw new ParamsException("参数错误");
        }
        String hashedPassword = Hashing.getHashedString(password);
        if (salesmanMapper.selectSalesmanById(id) != null) {
            throw new IdDuplicatedException("账户已激活");
        }
        Salesman salesman = new Salesman(id, hashedPassword);
        Integer result = salesmanMapper.insertSalesmanSelective(salesman);
        if (result != 1) {
            throw new ServerException("服务器错误");
        }
    }

    @Override
    public void salesmanLogin(String id, String password) throws ParamsException, IdPasswordErrorException {
        if (id == null || password == null) {
            throw new ParamsException("参数错误");
        }
        Salesman result = salesmanMapper.selectSalesmanById(id);
        String hashedPassword = Hashing.getHashedString(password);
        if (result == null || !result.getHashedPassword().equals(hashedPassword)) {
            throw new IdPasswordErrorException("账户或密码错误");
        }
    }

    @Override
    public void salesmanModifyInformation(Salesman salesman) {
        Integer result = salesmanMapper.updateSalesmanSelectiveById(salesman);
        if (result != 1) {
            throw new ServerException("服务器错误");
        }
    }
}