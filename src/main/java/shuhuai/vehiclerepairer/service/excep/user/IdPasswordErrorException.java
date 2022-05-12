package shuhuai.vehiclerepairer.service.excep.user;

import shuhuai.vehiclerepairer.service.excep.BaseException;

public class IdPasswordErrorException extends BaseException {
    public IdPasswordErrorException(String message) {
        super(message);
    }
}