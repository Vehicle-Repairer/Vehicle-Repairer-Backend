package shuhuai.vehiclerepairer.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shuhuai.vehiclerepairer.entity.*;
import shuhuai.vehiclerepairer.response.Response;
import shuhuai.vehiclerepairer.service.*;
import shuhuai.vehiclerepairer.utils.TokenValidator;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/attorney")
@Api(tags = "维修委托书接口")
public class AttorneyController extends BaseController {
    @Resource
    private AttorneyService attorneyService;
    @Resource
    private ConsumptionService consumptionService;
    @Resource
    private AssignmentService assignmentService;
    @Resource
    private CustomerService customerService;
    @Resource
    private UserService userService;

    @ApiOperation("添加维修委托书")
    @RequestMapping(value = "/add-attorney", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "添加维修委托书成功"),
            @ApiResponse(code = 403, message = "权限不足"),
            @ApiResponse(code = 422, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器错误")
    })

    public Response<Object> addAttorney(@RequestParam Integer customerId, @RequestParam String frameNumber, @RequestParam String licenseNumber,
                                        @RequestParam String repairType, @RequestParam String repairAmount, @RequestParam Integer range,
                                        @RequestParam String fuelAmount, @RequestParam Boolean isFinished,
                                        @RequestParam String detailedFault,
                                        @RequestParam String payType) {
        String id = TokenValidator.getUser().get("id");
        Salesman salesman = userService.getSalesman(id);
        String manName = salesman.getManName();
        Date inFactoryTime = new Date();
        attorneyService.addAttorney(customerId, frameNumber, licenseNumber, repairType, repairAmount, range, fuelAmount, id, manName, isFinished, detailedFault,
                 inFactoryTime,payType);
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
        for (Attorney attorney : attorneys) {
            if(attorney.getFinished()){
                attorney.setIsFinishedString("已完成");
            }
            else{
                attorney.setIsFinishedString("未完成");
            }
        }
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

    public Response<Object> getAttorneyBySalesmanId(Boolean isFinished) {
        String id = TokenValidator.getUser().get("id");
        List<Attorney> attorneys = attorneyService.getAttorneyBySalesmanId(id,isFinished);
        for (Attorney attorney : attorneys) {
            if(attorney.getFinished()){
                attorney.setIsFinishedString("已完成");
            }
            else{
                attorney.setIsFinishedString("未完成");
            }
        }
        return new Response<>(200, "查询业务员的维修委托书成功", new HashMap<String, Object>() {{
            put("attorneys", attorneys);
        }});
    }

    @ApiOperation("完成维修委托书")
    @RequestMapping(value = "/finish", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 403, message = "权限不足"),
            @ApiResponse(code = 422, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器错误")
    })

    public Response<Object> finish(@RequestParam Integer attorneyId,@RequestParam Boolean isFinished) {
        Attorney attorney = new Attorney();
        attorney.setAttorneyId(attorneyId);
        attorney.setFinished(isFinished);
        attorneyService.updateAttorney(attorney);
        return new Response<>(200, "维修委托书状态修改成功",null);
    }

    @ApiOperation("维修最终价格")
    @RequestMapping(value = "/total-price", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 403, message = "权限不足"),
            @ApiResponse(code = 422, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器错误")
    })

    public Response<Object> totalPrice(@RequestParam Integer attorneyId) {
        Attorney attorney = attorneyService.selectAttorneyById(attorneyId);
        if (attorney == null) {
            return new Response<>(403, "维修委托书不存在", null);
        }
        List<Assignment>  assignment = assignmentService.getAssignmentByAttorneyId(attorneyId);
        if (assignment.size() == 0) {
            return new Response<>(200, "没有维修派工单", null);
        }
        BigDecimal part = new BigDecimal(0);
        for(Assignment assignment1:assignment){
            part = part.add(consumptionService.getPartPrice(assignment1.getAssignmentId()));
        }
        BigDecimal man = assignmentService.attorneyRepairmanPrice(attorneyId);
        Customer customer = customerService.getCustomer(attorney.getCustomerId());
        double discount_rate = customer.getDiscountRate()/100;
        BigDecimal discount = new BigDecimal(discount_rate);
        BigDecimal total = part.add(man);
        total = total.multiply(discount);
        FinalPrice finalPrice = new FinalPrice(man,part,discount_rate*100,total);
        return new Response<>(200, "价格获取成功",new HashMap<String, Object>() {{
            put("价格明细", finalPrice);
        }});
    }
}
