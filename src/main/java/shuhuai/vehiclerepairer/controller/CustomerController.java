package shuhuai.vehiclerepairer.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shuhuai.vehiclerepairer.entity.Customer;
import shuhuai.vehiclerepairer.entity.Vehicle;
import shuhuai.vehiclerepairer.response.Response;
import shuhuai.vehiclerepairer.service.CustomerService;
import shuhuai.vehiclerepairer.service.VehicleService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

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
        Customer customer = customerService.getCustomer(customerId);
        return new Response<>(200, "获取客户信息成功", new HashMap<String, Object>() {{
            put("customer", customer);
        }});
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

    @ApiOperation("获取客户车辆信息")
    @RequestMapping(value = "/vehicle", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "获取客户车辆信息成功"),
            @ApiResponse(code = 403, message = "权限不足"),
            @ApiResponse(code = 422, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    public Response<Object> getVehicle(@RequestParam Integer customerId) {
        List<Vehicle> vehicles = vehicleService.getVehicleByCustomerId(customerId);
        return new Response<>(200, "获取客户车辆信息成功", new HashMap<String, List<Vehicle>>() {{
            put("vehicles", vehicles);
        }});
    }

    @ApiOperation("电话获取客户列表")
    @RequestMapping(value = "/phone", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "电话获取客户列表成功"),
            @ApiResponse(code = 403, message = "权限不足"),
            @ApiResponse(code = 422, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    public Response<Object> getCustomer(@RequestParam String phone) {
        List<Customer> customers = customerService.getCustomer(phone);
        return new Response<>(200, "电话获取客户列表成功", new HashMap<String, List<Customer>>() {{
            put("customers", customers);
        }});
    }
}