package shuhuai.vehiclerepairer.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shuhuai.vehiclerepairer.response.Response;
import shuhuai.vehiclerepairer.response.datatransferobject.LoginResponse;
import shuhuai.vehiclerepairer.type.Role;
import shuhuai.vehiclerepairer.utils.TokenValidator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/test")
@Api(tags = "测试")
@Slf4j
public class TestController extends BaseContrller {
    @Resource
    TokenValidator tokenValidator;

    @ApiOperation("生成token")
    @RequestMapping(value = "/generate-token", method = RequestMethod.GET)
    public Response<LoginResponse> getToken(@RequestParam String account, @RequestParam Role role) {
        String token = tokenValidator.getToken(account, role);
        return new Response<>(200, "生成成功", new LoginResponse(token));
    }

    @ApiOperation("认证token")
    @RequestMapping(value = "/validate-token", method = RequestMethod.GET)
    public Response<Object> validateToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization").split(" ")[1];
        return new Response<>(200, "认证成功", tokenValidator.parseToken(token));
    }
}