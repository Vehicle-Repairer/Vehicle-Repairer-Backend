package shuhuai.vehiclerepairer.service;

import shuhuai.vehiclerepairer.entity.Repairman;
import shuhuai.vehiclerepairer.entity.Salesman;
import shuhuai.vehiclerepairer.service.excep.AccountDuplicatedException;
import shuhuai.vehiclerepairer.service.excep.ServerException;

/*** 用户模块业务层接口
 * @author 殊怀丶
 * @version 1.0
 */
public interface UserService {
    /**
     * 维修员登录
     *
     * @param account  用户账号
     * @param password 用户密码
     */
    void repairmanRegister(String account, String password) throws AccountDuplicatedException, ServerException;

    /**
     * 维修员登录
     *
     * @param repairman 维修员实例
     */
    void repairmanLogin(Repairman repairman);
    /**
     * 业务员登录
     *
     * @param account  用户账号
     * @param password 用户密码
     */
    void salesmanRegister(String account, String password) throws AccountDuplicatedException, ServerException;

    /**
     * 业务员登录
     *
     * @param salesman 业务员实例
     */
    void salesmanLogin(Salesman salesman);
}
