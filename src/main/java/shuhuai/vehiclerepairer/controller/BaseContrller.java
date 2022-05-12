package shuhuai.vehiclerepairer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import shuhuai.vehiclerepairer.response.Response;
import shuhuai.vehiclerepairer.service.excep.BaseException;
import shuhuai.vehiclerepairer.service.excep.common.ParamsException;
import shuhuai.vehiclerepairer.service.excep.common.ServerException;
import shuhuai.vehiclerepairer.service.excep.common.TokenExpireException;
import shuhuai.vehiclerepairer.service.excep.user.IdDuplicatedException;
import shuhuai.vehiclerepairer.service.excep.user.IdPasswordErrorException;

@Slf4j
public class BaseContrller {
    @ExceptionHandler(BaseException.class)
    public Response<Object> handleServiceException(BaseException e) {
        Response<Object> response = new Response<>();
        if (e instanceof IdDuplicatedException) {
            response.setCode(400);
        } else if (e instanceof IdPasswordErrorException | e instanceof TokenExpireException) {
            response.setCode(401);
        } else if (e instanceof ParamsException) {
            response.setCode(422);
        } else if (e instanceof ServerException) {
            response.setCode(500);
        }
        response.setMessage(e.getMessage());
        return response;
    }
}