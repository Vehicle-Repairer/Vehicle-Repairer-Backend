package shuhuai.vehiclerepairer.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import shuhuai.vehiclerepairer.response.Response;
import shuhuai.vehiclerepairer.service.excep.AccountDuplicatedException;
import shuhuai.vehiclerepairer.service.excep.ServerException;
import shuhuai.vehiclerepairer.service.excep.ServiceException;

public class BaseContrller {
    public static final int OK = 200;

    @ExceptionHandler(ServiceException.class)
    public Response handleServiceException(ServiceException e) {
        Response response = new Response();
        if (e instanceof AccountDuplicatedException) {
            response.setCode(401);
        }
        if (e instanceof ServerException) {
            response.setCode(500);
        }
        response.setMessage(e.getMessage());
        return response;
    }
}