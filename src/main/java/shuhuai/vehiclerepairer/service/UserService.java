package shuhuai.vehiclerepairer.service;

import shuhuai.vehiclerepairer.service.excep.common.ParamsException;
import shuhuai.vehiclerepairer.service.excep.common.ServerException;
import shuhuai.vehiclerepairer.service.excep.user.AccountDuplicatedException;
import shuhuai.vehiclerepairer.service.excep.user.AccountPasswordErrorException;

/*** 用户模块业务层接口
 * @author 殊怀丶
 * @version 1.0
 */
public interface UserService {
    /**
     * 维修员激活
     *
     * @param account  用户账号
     * @param password 用户密码
     */
    void repairmanActive(String account, String password) throws AccountDuplicatedException, ParamsException, ServerException;

    /**
     * 维修员登录
     *
     * @param account  用户账号
     * @param password 用户密码
     */
    void repairmanLogin(String account, String password) throws ParamsException, AccountPasswordErrorException;

    /**
     * 业务员激活
     *
     * @param account  用户账号
     * @param password 用户密码
     */
    void salesmanActive(String account, String password) throws AccountDuplicatedException, ParamsException, ServerException;

    /**
     * 业务员登录
     *
     * @param account  用户账号
     * @param password 用户密码
     */
    void salesmanLogin(String account, String password) throws ParamsException, AccountPasswordErrorException;
}