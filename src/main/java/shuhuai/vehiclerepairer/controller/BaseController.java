package shuhuai.vehiclerepairer.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import shuhuai.vehiclerepairer.response.Response;
import shuhuai.vehiclerepairer.service.excep.BaseException;
import shuhuai.vehiclerepairer.service.excep.common.ForbiddenException;
import shuhuai.vehiclerepairer.service.excep.common.ParamsException;
import shuhuai.vehiclerepairer.service.excep.common.ServerException;
import shuhuai.vehiclerepairer.service.excep.common.TokenExpireException;
import shuhuai.vehiclerepairer.service.excep.user.IdDuplicatedException;
import shuhuai.vehiclerepairer.service.excep.user.IdPasswordErrorException;

public class BaseController {
    @ExceptionHandler(BaseException.class)
    public Response<Object> handleServiceException(BaseException error) {
        Response<Object> response = new Response<>();
        if (error instanceof IdDuplicatedException) {
            response.setCode(400);
        } else if (error instanceof IdPasswordErrorException | error instanceof TokenExpireException) {
            response.setCode(401);
        } else if (error instanceof ForbiddenException) {
            response.setCode(403);
        } else if (error instanceof ParamsException) {
            response.setCode(422);
        } else if (error instanceof ServerException) {
            response.setCode(500);
        }
        response.setMessage(error.getMessage());
        return response;
    }
}