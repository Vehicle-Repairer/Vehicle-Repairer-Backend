package shuhuai.vehiclerepairer.service.excep.user;

import shuhuai.vehiclerepairer.service.excep.BaseException;

public class AccountPasswordErrorException extends BaseException {
    public AccountPasswordErrorException(String message) {
        super(message);
    }
}