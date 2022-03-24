package shuhuai.vehiclerepairer.controller;

import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shuhuai.vehiclerepairer.response.Response;
import shuhuai.vehiclerepairer.response.datatransferobject.LoginResponse;
import shuhuai.vehiclerepairer.service.UserService;
import shuhuai.vehiclerepairer.utils.TokenValidator;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/user")
@Api(tags = "用户管理")
@Slf4j
public class UserController extends BaseContrller {
    @Resource
    private UserService userService;
    @Resource
    private TokenValidator tokenValidator;

    @ApiOperation("维修员激活")
    @RequestMapping(value = "/repairman-active", method = RequestMethod.POST)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "account", value = "账户"),
            @ApiImplicitParam(name = "password", value = "密码")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "维修员激活成功"),
            @ApiResponse(code = 400, message = "账户已激活"),
            @ApiResponse(code = 422, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    public Response repairmanActvie(@RequestParam String account, @RequestParam String password) {
        userService.repairmanActive(account, password);
        Response response = new Response(200, "维修员激活成功", null);
        log.info("/api/user/repairman-active：" + response.getCode() + "，" + response.getMessage());
        return response;
    }

    @ApiOperation("维修员登录")
    @RequestMapping(value = "/repairman-login", method = RequestMethod.POST)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "account", value = "账户"),
            @ApiImplicitParam(name = "password", value = "密码")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "维修员登录成功"),
            @ApiResponse(code = 401, message = "账户或密码错误"),
            @ApiResponse(code = 422, message = "参数错误"),
    })
    public Response repairmanLogin(@RequestParam String account, @RequestParam String password) {
        userService.repairmanLogin(account, password);
        String token = tokenValidator.getToken(account, "Repairman");
        Response response = new Response(200, "维修员登录成功", new LoginResponse(token));
        log.info("/api/user/repairman-login：" + response.getCode() + "，" + response.getMessage());
        return response;
    }

    @ApiOperation("业务员激活")
    @RequestMapping(value = "/salesman-active", method = RequestMethod.POST)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "account", value = "账户"),
            @ApiImplicitParam(name = "password", value = "密码")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "业务员激活成功"),
            @ApiResponse(code = 400, message = "账户已激活"),
            @ApiResponse(code = 422, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    public Response salesmanActive(@RequestParam String account, @RequestParam String password) {
        userService.salesmanActive(account, password);
        Response response = new Response(200, "业务员激活成功", null);
        log.info("/api/user/salesman-active：" + response.getCode() + "，" + response.getMessage());
        return response;
    }

    @ApiOperation("业务员登录")
    @RequestMapping(value = "/salesman-login", method = RequestMethod.POST)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "account", value = "账户"),
            @ApiImplicitParam(name = "password", value = "密码")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "业务员激活成功"),
            @ApiResponse(code = 401, message = "账户或密码错误"),
            @ApiResponse(code = 422, message = "参数错误")
    })
    public Response salesmanLogin(@RequestParam String account, @RequestParam String password) {
        userService.salesmanLogin(account, password);
        String token = tokenValidator.getToken(account, "Salesman");
        Response response = new Response(200, "业务员登录成功", new LoginResponse(token));
        log.info("/api/user/salesman-active：" + response.getCode() + "，" + response.getMessage());
        return response;
    }
}