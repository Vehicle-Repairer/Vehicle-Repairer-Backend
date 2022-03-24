package shuhuai.vehiclerepairer.service.excep.common;

import shuhuai.vehiclerepairer.service.excep.BaseException;

/*** 插入数据库异常的类
 * @author 殊怀 丶
 * @version 1.0
 */
public class ServerException extends BaseException {
    public ServerException(String message) {
        super(message);
    }
}