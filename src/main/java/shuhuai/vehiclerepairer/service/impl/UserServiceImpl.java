package shuhuai.vehiclerepairer.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import shuhuai.vehiclerepairer.entity.Repairman;
import shuhuai.vehiclerepairer.entity.Salesman;
import shuhuai.vehiclerepairer.mapper.RepairmanMapper;
import shuhuai.vehiclerepairer.mapper.SalesmanMapper;
import shuhuai.vehiclerepairer.service.UserService;
import shuhuai.vehiclerepairer.service.excep.common.ParamsException;
import shuhuai.vehiclerepairer.service.excep.common.ServerException;
import shuhuai.vehiclerepairer.service.excep.user.AccountDuplicatedException;
import shuhuai.vehiclerepairer.service.excep.user.AccountPasswordErrorException;
import shuhuai.vehiclerepairer.utils.Hashing;

import javax.annotation.Resource;

/*** 用户服务实现类
 @author 殊怀丶
 @version 1.0
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    RepairmanMapper repairmanMapper;
    @Resource
    SalesmanMapper salesmanMapper;

    @Override
    public void repairmanActive(String account, String password) throws AccountDuplicatedException, ParamsException, ServerException {
        if (account == null || password == null) {
            log.error("/api/user/repairman-active：参数错误");
            throw new ParamsException("参数错误");
        }
        String hashedPassword = Hashing.getHashedString(password);
        if (repairmanMapper.selectRepairmanByAccount(account) != null) {
            log.error("/api/user/repairman-active：账户已激活");
            throw new AccountDuplicatedException("账户已激活");
        }
        Repairman repairman = new Repairman(account, hashedPassword);
        int result = repairmanMapper.insertRepairmanSelective(repairman);
        if (result != 1) {
            log.error("/api/user/repairman-active：服务器错误");
            throw new ServerException("服务器错误");
        }
    }

    @Override
    public void repairmanLogin(String account, String password) throws ParamsException, AccountPasswordErrorException {
        if (account == null || password == null) {
            log.error("/api/user/repairman-login：参数错误");
            throw new ParamsException("参数错误");
        }
        Repairman result = repairmanMapper.selectRepairmanByAccount(account);
        String hashedPassword = Hashing.getHashedString(password);
        if (result == null || !result.getHashedPassword().equals(hashedPassword)) {
            log.error("/api/user/repairman-login：账户或密码错误");
            throw new AccountPasswordErrorException("账户或密码错误");
        }
    }

    @Override
    public void salesmanActive(String account, String password) throws AccountDuplicatedException, ParamsException, ServerException {
        if (account == null || password == null) {
            log.error("/api/user/salesman-active：参数错误");
            throw new ParamsException("参数错误");
        }
        String hashedPassword = Hashing.getHashedString(password);
        if (salesmanMapper.selectSalesmanByAccount(account) != null) {
            log.error("/api/user/salesman-active：账户已激活");
            throw new AccountDuplicatedException("账户已激活");
        }
        Salesman salesman = new Salesman(account, hashedPassword);
        int result = salesmanMapper.insertSalesmanSelective(salesman);
        if (result != 1) {
            log.error("/api/user/salesman-active：服务器错误");
            throw new ServerException("服务器错误");
        }
    }

    @Override
    public void salesmanLogin(String account, String password) throws ParamsException, AccountPasswordErrorException {
        if (account == null || password == null) {
            log.error("/api/user/salesman-login：参数错误");
            throw new ParamsException("参数错误");
        }
        Salesman result = salesmanMapper.selectSalesmanByAccount(account);
        String hashedPassword = Hashing.getHashedString(password);
        if (result == null || !result.getHashedPassword().equals(hashedPassword)) {
            log.error("/api/user/repairman-login：账户或密码错误");
            throw new AccountPasswordErrorException("账户或密码错误");
        }
    }
}
