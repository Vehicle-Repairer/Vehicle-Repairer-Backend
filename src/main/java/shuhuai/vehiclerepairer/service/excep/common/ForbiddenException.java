package shuhuai.vehiclerepairer.service.excep.common;

import shuhuai.vehiclerepairer.service.excep.BaseException;

public class ForbiddenException extends BaseException {
    public ForbiddenException(String message) {
        super(message);
    }
}