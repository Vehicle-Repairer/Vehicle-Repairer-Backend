package shuhuai.vehiclerepairer.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shuhuai.vehiclerepairer.entity.Assignment;
import shuhuai.vehiclerepairer.response.Response;
import shuhuai.vehiclerepairer.service.AssignmentService;


import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/assignment")
@Api(tags = "维修派工单接口")
public class AssignmentController {
    @Resource
    private AssignmentService assignmentService;

    @ApiOperation("添加维修派工单")
    @RequestMapping(value = "/add-assignment", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "添加维修派工单成功"),
            @ApiResponse(code = 403, message = "权限不足"),
            @ApiResponse(code = 422, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    public Response<Object> addAssignment(@RequestParam Integer attorneyId,@RequestParam Integer itemId,
                                          @RequestParam String repairmanId,@RequestParam Boolean isFinished) {
        assignmentService.addAssignment(attorneyId,itemId,isFinished,repairmanId);
        return new Response<>(200, "添加成功", null);
    }

    @ApiOperation("查询该委托书的维修派工单")
    @RequestMapping(value = "/assignment", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "查询该委托书的维修派工单成功"),
            @ApiResponse(code = 403, message = "权限不足"),
            @ApiResponse(code = 422, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器错误")
    })

    public Response<Object> getAssignmentByAttorneyId(@RequestParam Integer attorneyId) {
        List<Assignment> assignments = assignmentService.getAssignmentByAttorneyId(attorneyId);
        return new Response<>(200, "查询该委托书的维修派工单成功", new HashMap<String, Object>() {{
            put("assignments", assignments);
        }});
    }


    @ApiOperation("查询维修工的维修派工单")
    @RequestMapping(value = "/assignment-by-repairman", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 403, message = "权限不足"),
            @ApiResponse(code = 422, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器错误")
    })

    public Response<Object> getAssignmentByRepairman(@RequestParam String repairmanId) {
        List<Assignment> assignments = assignmentService.getAssignmentByRepairman(repairmanId);
        return new Response<>(200, "查询该委托书的维修派工单成功", new HashMap<String, Object>() {{
            put("assignments", assignments);
        }});
    }

    @ApiOperation("完成维修派工单")
    @RequestMapping(value = "/finish-assignment", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 403, message = "权限不足"),
            @ApiResponse(code = 422, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器错误")
    })

    public Response<Object> finishAssignment(@RequestParam Integer assignmentId,@RequestParam boolean isFinished) {
        Assignment assignment = new Assignment();
        assignment.setAssignmentId(assignmentId);
        assignment.setFinished(isFinished);
        assignmentService.updateAssignment(assignment);
        return new Response<>(200, "维修派工单状态修改成功",null);
    }
}
