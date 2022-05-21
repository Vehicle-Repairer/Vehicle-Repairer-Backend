package shuhuai.vehiclerepairer.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shuhuai.vehiclerepairer.entity.RepairItem;
import shuhuai.vehiclerepairer.response.Response;
import shuhuai.vehiclerepairer.service.RepairItemService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/repairItem")
@Api(tags = "维修内容接口")
public class RepairItemController extends BaseController {
    @Resource
    private RepairItemService repairItemService;

    @ApiOperation("添加维修内容")
    @RequestMapping(value = "/add-repair-item", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 403, message = "权限不足"),
            @ApiResponse(code = 422, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    public Response<Object> addItem(@RequestParam String itemName, @RequestParam Integer needTime, @RequestParam String profession) {
        repairItemService.addRepairItem(itemName, needTime, profession);
        return new Response<>(200, "添加成功", null);
    }

    @ApiOperation("修改维修内容")
    @RequestMapping(value = "/update-repair-item", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 403, message = "权限不足"),
            @ApiResponse(code = 422, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    public Response<Object> updateItem(@RequestParam Integer partId, String itemName,
                                       Integer needTime, String profession) {
        RepairItem repairItem = new RepairItem(partId, itemName, needTime, profession);
        repairItemService.updateRepairItem(repairItem);
        return new Response<>(200, "修改成功", null);
    }

    @ApiOperation("由id得维修内容")
    @RequestMapping(value = "/detailed-item", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 403, message = "权限不足"),
            @ApiResponse(code = 422, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    public Response<Object> Item(@RequestParam Integer itemId) {
        return new Response<>(200, "修改成功", new HashMap<String,RepairItem>(){{
            put("repairItem",repairItemService.getRepairItem(itemId));
        }});
    }

    @ApiOperation("取得维修内容   可查询全部，特定工种，特定内容，或工种及内容")
    @RequestMapping(value = "/get-repair-items", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 403, message = "权限不足"),
            @ApiResponse(code = 422, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    public Response<Object> getItems(String itemName, String profession) {
        List<RepairItem> repairItemList;
        if (itemName == null && profession != null) {
            repairItemList = repairItemService.getRepairItemsByProfession(profession);
        } else if (itemName != null && profession == null) {
            repairItemList = repairItemService.getRepairItemsByItemName(itemName);
        } else if (itemName != null) {
            repairItemList = repairItemService.getRepairItemsByProAndName(profession, itemName);
        } else {
            repairItemList = repairItemService.getAllRepairItems();
        }
        return new Response<>(200, "获取维修项目成功", new HashMap<String, List<RepairItem>>() {{
            put("维修项目信息", repairItemList);
        }});
    }

}
