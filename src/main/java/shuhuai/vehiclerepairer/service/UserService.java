package shuhuai.vehiclerepairer.service;

import shuhuai.vehiclerepairer.entity.Repairman;
import shuhuai.vehiclerepairer.entity.Salesman;
import shuhuai.vehiclerepairer.service.excep.common.ParamsException;
import shuhuai.vehiclerepairer.service.excep.common.ServerException;
import shuhuai.vehiclerepairer.service.excep.user.IdDuplicatedException;
import shuhuai.vehiclerepairer.service.excep.user.IdPasswordErrorException;

/*** 用户模块业务层接口
 * @author 殊怀丶
 * @version 1.0
 */
public interface UserService {
    /**
     * 维修员激活
     *
     * @param id       用户账号
     * @param password 用户密码
     */
    void repairmanActive(String id, String password) throws IdDuplicatedException, ParamsException, ServerException;

    /**
     * 维修员登录
     *
     * @param id       用户账号
     * @param password 用户密码
     */
    void repairmanLogin(String id, String password) throws ParamsException, IdPasswordErrorException;

    void repairmanModifyInformation(Repairman repairman);

    /**
     * 业务员激活
     *
     * @param id       用户账号
     * @param password 用户密码
     */
    void salesmanActive(String id, String password) throws IdDuplicatedException, ParamsException, ServerException;

    /**
     * 业务员登录
     *
     * @param id       用户账号
     * @param password 用户密码
     */
    void salesmanLogin(String id, String password) throws ParamsException, IdPasswordErrorException;

    void salesmanModifyInformation(Salesman salesman);
}