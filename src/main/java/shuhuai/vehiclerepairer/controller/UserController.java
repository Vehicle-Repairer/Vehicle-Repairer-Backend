package shuhuai.vehiclerepairer.controller;

import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shuhuai.vehiclerepairer.entity.Repairman;
import shuhuai.vehiclerepairer.entity.Salesman;
import shuhuai.vehiclerepairer.response.Response;
import shuhuai.vehiclerepairer.response.datatransferobject.LoginResponse;
import shuhuai.vehiclerepairer.service.UserService;
import shuhuai.vehiclerepairer.service.excep.common.ParamsException;
import shuhuai.vehiclerepairer.utils.TokenValidator;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Date;

@RestController
@RequestMapping("/api/user")
@Api(tags = "用户管理")
@Slf4j
public class UserController extends BaseContrller {
    @Resource
    private UserService userService;
    @Resource
    private TokenValidator tokenValidator;

    @ApiOperation("激活")
    @RequestMapping(value = "/active", method = RequestMethod.POST)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "role", value = "维修员/业务员"),
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "激活成功"),
            @ApiResponse(code = 400, message = "账户已激活"),
            @ApiResponse(code = 422, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    public Response actvie(@RequestParam String role, @RequestParam String account, @RequestParam String password) {
        if (role.equals("维修员")) {
            userService.repairmanActive(account, password);
        } else if (role.equals("业务员")) {
            userService.salesmanActive(account, password);
        } else {
            throw new ParamsException("参数错误");
        }
        Response response = new Response(200, "激活成功", null);
        log.info("/api/user/active：" + response.getMessage());
        return response;
    }

    @ApiOperation("登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "role", value = "维修员/业务员"),
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "登录成功"),
            @ApiResponse(code = 401, message = "账户或密码错误"),
            @ApiResponse(code = 422, message = "参数错误"),
    })
    public Response login(@RequestParam String role, @RequestParam String account, @RequestParam String password) {
        if (role.equals("维修员")) {
            userService.repairmanLogin(account, password);
        } else if (role.equals("业务员")) {
            userService.salesmanLogin(account, password);
        } else {
            throw new ParamsException("参数错误");
        }
        String token = tokenValidator.getToken(account, role);
        Response response = new Response(200, "登录成功", new LoginResponse(token));
        log.info("/api/user/login：" + response.getMessage());
        return response;
    }

    @ApiOperation("更改个人信息")
    @RequestMapping(value = "/modify-information", method = RequestMethod.POST)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "role", value = "维修员/业务员"),
            @ApiImplicitParam(name = "birthday", value = "生日"),
            @ApiImplicitParam(name = "hourCost", value = "工时单价",type = "number"),
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "更改个人信息成功"),
            @ApiResponse(code = 401, message = "token无效"),
            @ApiResponse(code = 422, message = "参数错误"),
    })
    public Response modifyInformation(@RequestParam String role, String manName, String sex, String phone, Date birthday, String address, String emailAddress,
                                      String profession, BigDecimal hourCost) {
        String account = TokenValidator.getUser().get("account");
        if (role.equals("维修员")) {
            Repairman repairman = new Repairman(null, account, manName, null, sex, phone, birthday, address, emailAddress, null,
                profession,hourCost);
            userService.repairmanModifyInformation(repairman);
        } else if (role.equals("业务员")) {
            Salesman salesman = new Salesman(null, account, manName, null, sex, phone, birthday, address, emailAddress, null);
            userService.salesmanModifyInformation(salesman);
        } else {
            throw new ParamsException("参数错误");
        }
        Response response = new Response(200, "更改个人信息成功", null);
        log.info("/api/user//modify-information：" + response.getMessage());
        return response;
    }
}