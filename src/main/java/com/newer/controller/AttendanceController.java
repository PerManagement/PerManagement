package com.newer.controller;

import com.github.pagehelper.PageInfo;
import com.newer.domain.Attendance;
import com.newer.domain.Department;
import com.newer.domain.User;
import com.newer.dto.PageDto;
import com.newer.service.AttendanceService;
import com.newer.service.UserService;
import com.newer.service.impl.AttendanceServiceImpl;
import com.newer.util.CommonsResult;
import com.newer.util.Sessions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("attendance")
@SessionAttributes(Sessions.SESSION_LOGIN_USER)
public class AttendanceController {

    @Autowired
    private AttendanceService abstractServiceimpl ;
    @Autowired
    private UserService userService;

    @GetMapping("pageInfo")
    public CommonsResult findAll(Integer page,Integer pageSize){
        PageDto dto=new PageDto();
        dto.setPage(page);
        dto.setPageSize(pageSize);
        System.out.println(dto);
        PageInfo pageInfo=this.abstractServiceimpl.findAll(dto);
        return new CommonsResult(200,"任务分页",pageInfo);
    }

    //删除s
    @GetMapping("delete")
    public CommonsResult doDelete(@RequestBody Integer id){
        int tag=this.abstractServiceimpl.delete(id);
        if (tag>0){
            return new CommonsResult(200,"删除考勤成功",id);
        }
        return new CommonsResult(200,"删除考勤失败",null);
    }



    //创建考勤表
    @GetMapping("save")
    public CommonsResult save(@RequestBody Attendance abttendance, ModelMap modelMap){
        User user = (User)modelMap.getAttribute(Sessions.SESSION_LOGIN_USER);
        abttendance.setUserid(user.getUserid());
        int a=this.abstractServiceimpl.sava(abttendance);
        if(a>0){
            return  new CommonsResult(200,"打卡成功",abttendance);
        }
        return  new CommonsResult(200,"打卡有误",null);
    }

    //修改考勤表
    @GetMapping("update")
    public CommonsResult doUpdate(@RequestBody Attendance abttendance){
        int a=this.abstractServiceimpl.update(abttendance);
        if(a>0){
            return  new CommonsResult(200,"修改成功",abttendance);
        }
        return  new CommonsResult(200,"修改有误",null);
    }


}

