package shuhuai.vehiclerepairer.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shuhuai.vehiclerepairer.entity.Parts;
import shuhuai.vehiclerepairer.entity.Repairman;
import shuhuai.vehiclerepairer.response.Response;
import shuhuai.vehiclerepairer.service.PartService;
import shuhuai.vehiclerepairer.service.RepairmanService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/repairman")
@Api(tags = "维修人员接口")
public class RepairmanController extends BaseController {
    @Resource
    private RepairmanService repairmanService;
    @Resource
    private PartService partService;

    @ApiOperation("由id得维修员")
    @RequestMapping(value = "/get-repairman", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "登记客户信息成功"),
            @ApiResponse(code = 403, message = "权限不足"),
            @ApiResponse(code = 422, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    public Response<Object> getRepairman(@RequestParam String repairmanId) {
        Repairman repairman = repairmanService.getRepairman(repairmanId);
        return new Response<>(200, "获取维修员成功", new HashMap<String, Repairman>() {{
            put("customerId", repairman);
        }});
    }

    @ApiOperation("由专业得维修员")
    @RequestMapping(value = "/get-repairman-by-profession", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "登记客户信息成功"),
            @ApiResponse(code = 403, message = "权限不足"),
            @ApiResponse(code = 422, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    public Response<Object> getRepairmans(@RequestParam String prof) {
        List<Repairman> repairmen = repairmanService.getRepairmanByPro(prof);
        return new Response<>(200, "获取维修员成功", new HashMap<String, List<Repairman>>() {{
            put("customerId", repairmen);
        }});
    }

    @ApiOperation("所有维修员")
    @RequestMapping(value = "/get-all-repairman", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "登记客户信息成功"),
            @ApiResponse(code = 403, message = "权限不足"),
            @ApiResponse(code = 422, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    public Response<Object> getRepairmans() {
        List<Repairman> repairmen = repairmanService.selectAllRepairman();
        return new Response<>(200, "获取维修员成功", new HashMap<String, List<Repairman>>() {{
            put("维修员信息：", repairmen);
        }});
    }

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
}
