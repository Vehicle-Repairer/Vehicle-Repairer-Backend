package shuhuai.vehiclerepairer.service.excep.user;

import shuhuai.vehiclerepairer.service.excep.BaseException;

/*** 账号重复异常
 * @author 殊怀丶
 * @version 1.0
 */
public class AccountDuplicatedException extends BaseException {
    public AccountDuplicatedException(String message) {
        super(message);
    }
}