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
    public Response getToken(@RequestParam String account, @RequestParam String role) {
        String token = tokenValidator.getToken(account, role);
        Response response = new Response(200, "生成成功", new LoginResponse(token));
        log.info("/api/test/generate-token：" + response.getCode() + "，" + response.getMessage());
        return response;
    }

    @ApiOperation("认证token")
    @RequestMapping(value = "/validate-token", method = RequestMethod.GET)
    public Response validateToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization").split(" ")[1];
        Response response = new Response(200, "认证成功", tokenValidator.parseToken(token));
        log.info("/api/test/validate-token：" + response.getCode() + "，" + response.getMessage());
        return response;
    }
}