package shuhuai.vehiclerepairer.controller;

import io.swagger.annotations.*;
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
import shuhuai.vehiclerepairer.type.Role;
import shuhuai.vehiclerepairer.type.Sex;
import shuhuai.vehiclerepairer.utils.TokenValidator;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Date;

@RestController
@RequestMapping("/api/user")
@Api(tags = "用户管理")
public class UserController extends BaseController {
    @Resource
    private UserService userService;
    @Resource
    private TokenValidator tokenValidator;

    @ApiOperation("激活")
    @RequestMapping(value = "/active", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "激活成功"),
            @ApiResponse(code = 400, message = "账户已激活"),
            @ApiResponse(code = 422, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    public Response<Object> actvie(@RequestParam Role role, @RequestParam String id, @RequestParam String password) {
        if (role == Role.维修员) {
            userService.repairmanActive(id, password);
        } else if (role == Role.业务员) {
            userService.salesmanActive(id, password);
        } else {
            throw new ParamsException("参数错误");
        }
        return new Response<>(200, "激活成功", null);
    }

    @ApiOperation("登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "登录成功"),
            @ApiResponse(code = 401, message = "账户或密码错误"),
            @ApiResponse(code = 422, message = "参数错误"),
    })
    public Response<LoginResponse> login(@RequestParam Role role, @RequestParam String id, @RequestParam String password) {
        if (role == Role.维修员) {
            userService.repairmanLogin(id, password);
        } else if (role == Role.业务员) {
            userService.salesmanLogin(id, password);
        } else {
            throw new ParamsException("参数错误");
        }
        String token = tokenValidator.getToken(id, role);
        return new Response<>(200, "登录成功", new LoginResponse(token));
    }

    @ApiOperation("更改个人信息")
    @RequestMapping(value = "/modify-information", method = RequestMethod.POST)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "birthday", value = "生日"),
            @ApiImplicitParam(name = "hourCost", value = "工时单价", type = "number"),
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "更改个人信息成功"),
            @ApiResponse(code = 401, message = "token无效"),
            @ApiResponse(code = 422, message = "参数错误"),
    })
    public Response<Object> modifyInformation(String manName, Sex sex, String phone, Date birthday, String address, String emailAddress,
                                              String profession, BigDecimal hourCost) {
        String id = TokenValidator.getUser().get("id");
        Role role = Role.valueOf(TokenValidator.getUser().get("role"));
        if (role == Role.维修员) {
            Repairman repairman = new Repairman(id, manName, null, sex, phone, birthday, address, emailAddress, null,
                    profession, hourCost);
            userService.repairmanModifyInformation(repairman);
        } else if (role == Role.业务员) {
            Salesman salesman = new Salesman(id, manName, null, sex, phone, birthday, address, emailAddress, null);
            userService.salesmanModifyInformation(salesman);
        } else {
            throw new ParamsException("参数错误");
        }
        return new Response<>(200, "更改个人信息成功", null);
    }
}