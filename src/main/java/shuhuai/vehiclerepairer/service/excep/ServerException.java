package shuhuai.vehiclerepairer.service.excep;

/*** 这个类是用来描述插入数据库异常的类
 * @author 殊怀 丶
 * @version 1.0
 */
public class ServerException extends ServiceException {
    public ServerException() {
        super();
    }

    public ServerException(String message) {
        super(message);
    }

    public ServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServerException(Throwable cause) {
        super(cause);
    }

    protected ServerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
