package shuhuai.vehiclerepairer.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import shuhuai.vehiclerepairer.entity.Repairman;
import shuhuai.vehiclerepairer.entity.Salesman;
import shuhuai.vehiclerepairer.mapper.RepairmanMapper;
import shuhuai.vehiclerepairer.mapper.SalesmanMapper;
import shuhuai.vehiclerepairer.service.UserService;
import shuhuai.vehiclerepairer.service.excep.AccountDuplicatedException;
import shuhuai.vehiclerepairer.service.excep.ParamsException;
import shuhuai.vehiclerepairer.service.excep.ServerException;

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
    public void repairmanRegister(String account, String password) throws AccountDuplicatedException, ServerException {
        if (account == null || password == null) {
            throw new ParamsException("参数错误");
        }
        String hashedPassword = DigestUtils.md5DigestAsHex(password.getBytes()).toUpperCase();
        Repairman repairman = new Repairman(account, hashedPassword);
        if (repairmanMapper.selectRepairmanByAccount(repairman.getAccount()) != null) {
            throw new AccountDuplicatedException("账户已存在");
        }
        int result = repairmanMapper.insertRepairmanSelective(repairman);
        if (result != 1) {
            throw new ServerException("服务器异常");
        }
    }

    @Override
    public void repairmanLogin(Repairman repairman) {
        //TODO: 维修员登陆
    }

    @Override
    public void salesmanRegister(String account, String password) throws AccountDuplicatedException, ServerException {
        if (account == null || password == null) {
            throw new ParamsException("参数错误");
        }
        String hashedPassword = DigestUtils.md5DigestAsHex(password.getBytes()).toUpperCase();
        Salesman salesman = new Salesman(account, hashedPassword);
        if (salesmanMapper.selectSalesmanByAccount(salesman.getAccount()) != null) {
            throw new AccountDuplicatedException("账户已存在");
        }
        int result = salesmanMapper.insertSalesmanSelective(salesman);
        if (result != 1) {
            throw new ServerException("服务器异常");
        }
    }

    @Override
    public void salesmanLogin(Salesman salesman) {
        //TODO: 业务员登陆
    }
}
