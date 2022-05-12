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
import shuhuai.vehiclerepairer.service.VehicleService;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@RequestMapping("/api/customer")
@Api(tags = "客户管理")
public class CustomerController extends BaseController {
    @Resource
    private CustomerService customerService;
    @Resource
    private VehicleService vehicleService;

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
        Integer customerId = customerService.addCustomer(customerName, customerType, discountRate, contactPerson, phone);
        return new Response<>(200, "登记客户信息成功", new HashMap<String, Integer>() {{
            put("customerId", customerId);
        }});
    }

    @ApiOperation("获取客户信息")
    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "获取客户信息成功"),
            @ApiResponse(code = 403, message = "权限不足"),
            @ApiResponse(code = 422, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    public Response<Object> getCustomer(@RequestParam Integer customerId) {
        return new Response<>(200, "获取客户信息成功", customerService.getCustomer(customerId));
    }

    @ApiOperation("记录客户车辆信息")
    @RequestMapping(value = "/add-vehicle", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "记录客户车辆信息成功"),
            @ApiResponse(code = 403, message = "权限不足"),
            @ApiResponse(code = 422, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    public Response<Object> addVehicle(@RequestParam String frameNumber, @RequestParam String licenseNumber, @RequestParam Integer customerId,
                                       @RequestParam String color, @RequestParam String vehicleModel, @RequestParam String vehicleType) {
        vehicleService.addVehicle(frameNumber, licenseNumber, customerId, color, vehicleModel, vehicleType);
        return new Response<>(200, "记录客户车辆信息成功", null);
    }
}