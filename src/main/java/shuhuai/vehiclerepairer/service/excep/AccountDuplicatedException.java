package shuhuai.vehiclerepairer.service.excep;

/*** 账号重复异常
 * @author 殊怀丶
 * @version 1.0
 */
public class AccountDuplicatedException extends ServiceException {
    public AccountDuplicatedException() {
        super();
    }

    public AccountDuplicatedException(String message) {
        super(message);
    }

    public AccountDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountDuplicatedException(Throwable cause) {
        super(cause);
    }

    protected AccountDuplicatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
