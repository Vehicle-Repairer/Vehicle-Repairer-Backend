package shuhuai.vehiclerepairer.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shuhuai.vehiclerepairer.entity.*;
import shuhuai.vehiclerepairer.response.Response;
import shuhuai.vehiclerepairer.service.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/parts")
@Api(tags = "零件接口")
public class PartsController extends BaseController {
    @Resource
    private PartService partService;
    @Resource
    private ConsumptionService consumptionService;
    @Resource
    private AttorneyService attorneyService;
    @Resource
    private AssignmentService assignmentService;
    @Resource
    private CustomerService customerService;
    @Resource
    private UserService userService;
    @Resource
    private RepairItemService repairItemService;

    @ApiOperation("所有零件")
    @RequestMapping(value = "/get-all-parts", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "登记客户信息成功"),
            @ApiResponse(code = 403, message = "权限不足"),
            @ApiResponse(code = 422, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    public Response<Object> getAllParts() {
        List<Parts> parts = partService.getAllParts();
        return new Response<>(200, "获取零件成功", new HashMap<String, List<Parts>>() {{
            put("零件信息", parts);
        }});
    }

    @ApiOperation("添加零件")
    @RequestMapping(value = "/add-part", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "登记客户信息成功"),
            @ApiResponse(code = 403, message = "权限不足"),
            @ApiResponse(code = 422, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    public Response<Object> addPart(@RequestParam String partName, @RequestParam BigDecimal partPrice) {
        partService.addPart(partName, partPrice);
        return new Response<>(200, "添加成功", null);
    }

    @ApiOperation("修改零件")
    @RequestMapping(value = "/update-part", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "登记客户信息成功"),
            @ApiResponse(code = 403, message = "权限不足"),
            @ApiResponse(code = 422, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    public Response<Object> updatePart(@RequestParam Integer partId, String partName, BigDecimal partPrice) {
        Parts part = new Parts(partId, partName, partPrice);
        partService.updatePart(part);
        return new Response<>(200, "修改成功", null);
    }

    @ApiOperation("添加零件消耗")
    @RequestMapping(value = "/add-partConsumption", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "登记客户信息成功"),
            @ApiResponse(code = 403, message = "权限不足"),
            @ApiResponse(code = 422, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    public Response<Object> addPartConsumption(@RequestParam Integer assignmentId, @RequestParam Integer partId,
                                               @RequestParam Integer partAmount) {
        consumptionService.addConsumption(assignmentId, partId, partAmount);
        return new Response<>(200, "添加成功", null);
    }

    @ApiOperation("修改零件消耗")
    @RequestMapping(value = "/update-partConsumption", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "登记客户信息成功"),
            @ApiResponse(code = 403, message = "权限不足"),
            @ApiResponse(code = 422, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    public Response<Object> updatePartConsumption(@RequestParam Integer consumptionId, Integer assignmentId,
                                                  Integer partId, Integer partAmount) {
        Consumption consumption = new Consumption(consumptionId, assignmentId, partId, partAmount);
        consumptionService.updateConsumption(consumption);
        return new Response<>(200, "修改成功", null);
    }

    @ApiOperation("由派工单号得零件使用数量")
    @RequestMapping(value = "/get-consumption-by-assignment", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "登记客户信息成功"),
            @ApiResponse(code = 403, message = "权限不足"),
            @ApiResponse(code = 422, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    public Response<Object> getConsumptionByAssign(@RequestParam Integer assignmentId) {
        List<Consumption> consumptionList = consumptionService.getConsumptionsByAssignmentId(assignmentId);
        List<ConsumptionShow> consumptionShowList2 = consumptionService.getConsumptionShowByAssignmentId(assignmentId);
        return new Response<>(200, "获取成功", new HashMap<String, List<ConsumptionShow>>() {{
            put("零件消耗信息", consumptionShowList2);
        }});
    }

    @ApiOperation("由派工单号计算零件价格")
    @RequestMapping(value = "/get-price-by-assignment", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "登记客户信息成功"),
            @ApiResponse(code = 403, message = "权限不足"),
            @ApiResponse(code = 422, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    public Response<Object> getPriceByAssign(@RequestParam Integer assignmentId) {
        BigDecimal price = consumptionService.getPartPrice(assignmentId);
        return new Response<>(200, "获取成功", new HashMap<String, BigDecimal>() {{
            put("花费", price);
        }});
    }
}
