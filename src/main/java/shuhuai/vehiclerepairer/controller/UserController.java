package shuhuai.vehiclerepairer.controller;

import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shuhuai.vehiclerepairer.response.Response;
import shuhuai.vehiclerepairer.service.UserService;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/user")
@Api(tags = "用户管理")
@Slf4j
public class UserController extends BaseContrller {
    @Resource
    private UserService userService;

    @ApiOperation("维修员注册")
    @RequestMapping(value = "/repairman-register", method = RequestMethod.POST)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "account", value = "账户"),
            @ApiImplicitParam(name = "password", value = "密码")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "维修员注册成功"),
            @ApiResponse(code = 400, message = "参数错误"),
            @ApiResponse(code = 401, message = "账户已存在"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    public Response repairmanRegister(@RequestParam String account, @RequestParam String password) {
        userService.repairmanRegister(account, password);
        Response response = new Response(200, "维修员注册成功", null);
        log.info("/api/user/repairman-register：" + response.getCode() + "，" + response.getMessage());
        return response;
    }

    @ApiOperation("业务员注册")
    @RequestMapping(value = "/salesman-register", method = RequestMethod.POST)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "account", value = "账户"),
            @ApiImplicitParam(name = "password", value = "密码")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "业务员注册成功"),
            @ApiResponse(code = 400, message = "参数错误"),
            @ApiResponse(code = 401, message = "账户已存在"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    public Response salesmanRegister(@RequestParam String account, @RequestParam String password) {
        userService.salesmanRegister(account, password);
        Response response = new Response(200, "业务员注册成功", null);
        log.info("/api/user/salesman-register：" + response.getCode() + "，" + response.getMessage());
        return response;
    }
}
