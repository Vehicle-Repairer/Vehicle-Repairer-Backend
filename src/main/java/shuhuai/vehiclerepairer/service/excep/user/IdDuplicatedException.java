package shuhuai.vehiclerepairer.service.excep.user;

import shuhuai.vehiclerepairer.service.excep.BaseException;

/*** 账号重复异常
 * @author 殊怀丶
 * @version 1.0
 */
public class IdDuplicatedException extends BaseException {
    public IdDuplicatedException(String message) {
        super(message);
    }
}