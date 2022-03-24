package shuhuai.vehiclerepairer.service.excep.common;

import shuhuai.vehiclerepairer.service.excep.BaseException;

public class TokenExpireException extends BaseException {
    public TokenExpireException(String message) {
        super(message);
    }
}
