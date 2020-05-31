package com.newer.controller;

import com.github.pagehelper.PageInfo;
import com.newer.domain.Leave;
import com.newer.domain.User;
import com.newer.dto.PageDto;
import com.newer.service.LeaveService;
import com.newer.util.CommonsResult;
import com.newer.util.Sessions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 请假控制层
 */
@RestController
@RequestMapping("leave")
@SessionAttributes(Sessions.SESSION_LOGIN_USER)
public class LeaveController {
    @Autowired
    private LeaveService leaveService;
    @GetMapping("pageInfo")
    public CommonsResult findAll(Integer page, Integer pageSize){
        System.out.println(page+","+pageSize);
        PageDto dto=new PageDto();
        dto.setPage(page);
        dto.setPageSize(pageSize);
        PageInfo pageInfo=this.leaveService.findlhl(dto);
        return new CommonsResult(200,"请假分页",pageInfo);
    }
    @GetMapping("findlhl2")
    public CommonsResult findlhl2(Integer page, Integer pageSize){
        System.out.println(page+","+pageSize);
        PageDto dto=new PageDto();
        dto.setPage(page);
        dto.setPageSize(pageSize);
        PageInfo pageInfo=this.leaveService.findlhl2(dto);
        return new CommonsResult(200,"请假查询状态分页",pageInfo);
    }
    @GetMapping("findlhl3")
    public CommonsResult findlhl3(Integer page, Integer pageSize){
        System.out.println(page+","+pageSize);
        PageDto dto=new PageDto();
        dto.setPage(page);
        dto.setPageSize(pageSize);
        PageInfo pageInfo=this.leaveService.findlhl3(dto);
        return new CommonsResult(200,"请假Final分页",pageInfo);
    }
    @PostMapping("save")
    public CommonsResult save(@RequestBody Leave leave,ModelMap modelMap){
        User user = (User) modelMap.getAttribute(Sessions.SESSION_LOGIN_USER);
        System.out.println(user);
        leave.setApproval(user.getUpno());
//        leave.setApprover(user.getSjide());
        leave.setUserid(user.getUserid());
        leave.setLeavestate(null);
        leave.setFinall(null);
        leave.setRemark(null);
        leave.setRemarks(null);
        System.out.println(leave);
        int a=this.leaveService.save(leave);
        if(a>0){
            return  new CommonsResult(200,"成功",leave);
        }
        return  new CommonsResult(200,"有误",null);
    }
    @GetMapping("updatelhl")
    public CommonsResult updatelhl(@RequestParam Integer userid,Integer leaveid){
        System.out.println(userid);
        System.out.println(leaveid);
        int a=this.leaveService.updatelhl(userid,leaveid);
        if(a>0){
            System.out.println("成功");
            return  new CommonsResult(200,"审批成功",a);
        }
        return  new CommonsResult(200,"审批有误",null);
    }
    @GetMapping("updateState")
    public CommonsResult updateState(@RequestParam Integer leaveid){
        int a=this.leaveService.updateState(leaveid);
        if(a>0){
            System.out.println("审批成功");
            return  new CommonsResult(200,"审批成功",a);
        }
        return  new CommonsResult(200,"审批有误",null);
    }
    @GetMapping("updateState2")
    public CommonsResult updateState2(@RequestParam Integer leaveid){
        int a=this.leaveService.updateState2(leaveid);
        if(a>0){
            System.out.println("审批成功");
            return  new CommonsResult(200,"审批成功",a);
        }
        return  new CommonsResult(200,"审批有误",null);
    }
    @GetMapping("updateState3")
    public CommonsResult updateState3(@RequestParam Integer leaveid,Integer approver ,String remark){
        int a=this.leaveService.updateState3(leaveid,approver,remark);
        if(a>0){
            System.out.println("审批成功");
            return  new CommonsResult(200,"审批成功",a);
        }
        return  new CommonsResult(200,"审批有误",null);
    }
    @GetMapping("updateState6")
    public CommonsResult updateState6(@RequestParam Integer leaveid,Integer approval ,String remark){
        int a=this.leaveService.updateState6(leaveid,approval,remark);
        if(a>0){
            System.out.println("审批成功");
            return  new CommonsResult(200,"审批成功",a);
        }
        return  new CommonsResult(200,"审批有误",null);
    }
    @GetMapping("updateState5")
    public CommonsResult updateState5(  Integer leaveid){
        int a=this.leaveService.updateState5(leaveid);
        if(a>0){
            System.out.println("审批成功");
            return  new CommonsResult(200,"审批成功",a);
        }
        return  new CommonsResult(200,"审批有误",null);
    }
    @PostMapping("updatelhl2")
    public CommonsResult updatelhl2(@RequestParam Leave leave){
        int a=this.leaveService.updatelhl2(leave);
        if(a>0){
            return  new CommonsResult(200,"成功",a);
        }
        return  new CommonsResult(200,"重申请有误",null);
    }
}
