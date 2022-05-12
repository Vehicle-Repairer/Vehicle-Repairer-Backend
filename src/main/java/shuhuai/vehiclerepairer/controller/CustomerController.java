package shuhuai.vehiclerepairer.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shuhuai.vehiclerepairer.response.Response;
import shuhuai.vehiclerepairer.service.CustomerService;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/customer")
@Api(tags = "客户管理")
public class CustomerController extends BaseController {
    @Resource
    private CustomerService customerService;

    @ApiOperation("登记客户信息")
    @RequestMapping(value = "/add-customer", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "登记客户信息成功"),
            @ApiResponse(code = 403, message = "权限不足"),
            @ApiResponse(code = 422, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    public Response<Object> addCustomer(@RequestParam String customerName, @RequestParam String customerType, @RequestParam Double discountRate,
                                        @RequestParam String contactPerson, @RequestParam String phone) {
        customerService.addCustomer(customerName, customerType, discountRate, contactPerson, phone);
        return new Response<>(200, "登记客户信息成功", null);
    }
}