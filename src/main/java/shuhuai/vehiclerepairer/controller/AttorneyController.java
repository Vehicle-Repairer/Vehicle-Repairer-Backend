package shuhuai.vehiclerepairer.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shuhuai.vehiclerepairer.entity.Attorney;
import shuhuai.vehiclerepairer.response.Response;
import shuhuai.vehiclerepairer.service.AttorneyService;
import shuhuai.vehiclerepairer.utils.TokenValidator;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/attorney")
@Api(tags = "维修委托书接口")
public class AttorneyController {
    @Resource
    private AttorneyService attorneyService;

    @ApiOperation("添加维修委托书")
    @RequestMapping(value = "/add-attorney", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "添加维修委托书成功"),
            @ApiResponse(code = 403, message = "权限不足"),
            @ApiResponse(code = 422, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    public Response<Object> addAttorney(@RequestParam Integer customerId, @RequestParam String frameNumber, @RequestParam String licenseNumber, @RequestParam String repairType, @RequestParam String repairAmount,
                                        @RequestParam Integer range, @RequestParam String fuelAmount, @RequestParam String salesmanId, @RequestParam String manName, @RequestParam Boolean isFinished, @RequestParam String detailedFault,
                                        @RequestParam Date inFactoryTime, @RequestParam Double finalPrice) {
        attorneyService.addAttorney(customerId, frameNumber, licenseNumber, repairType, repairAmount,
                range, fuelAmount, salesmanId, manName, isFinished, detailedFault,
                inFactoryTime, finalPrice);
        return new Response<>(200, "添加成功", null);
    }

    @ApiOperation("查询客户的维修委托书")
    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "查询客户维修委托书成功"),
            @ApiResponse(code = 403, message = "权限不足"),
            @ApiResponse(code = 422, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器错误")
    })

    public Response<Object> getAttorneyByCustomerId(@RequestParam Integer customerId) {
        List<Attorney> attorneys = attorneyService.getAttorneyByCustomerId(customerId);
        return new Response<>(200, "查询客户维修委托书成功", new HashMap<String, Object>() {{
            put("attorneys", attorneys);
        }});
    }


    @ApiOperation("查询业务员的维修委托书")
    @RequestMapping(value = "/salesman", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "查询业务员的维修委托书成功"),
            @ApiResponse(code = 403, message = "权限不足"),
            @ApiResponse(code = 422, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器错误")
    })

    public Response<Object> getAttorneyBySalesmanId() {
        String id = TokenValidator.getUser().get("id");
        List<Attorney> attorneys = attorneyService.getAttorneyBySalesmanId(id);
        return new Response<>(200, "查询业务员的维修委托书成功", new HashMap<String, Object>() {{
            put("attorneys", attorneys);
        }});
    }
}
