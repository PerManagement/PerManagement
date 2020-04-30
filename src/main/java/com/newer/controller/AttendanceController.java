package com.newer.controller;

import com.newer.domain.Attendance;
import com.newer.domain.Task;
import com.newer.service.AttendanceService;
import com.newer.util.CommonsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService abstractService  ;

    //跳转考勤表界面
    @GetMapping("createTask")
    public CommonsResult goSave(){
        return new CommonsResult(200,"跳转创建任务界面","createTask");
    }

    //创建考勤表
    @GetMapping("save")
    public CommonsResult doSave(@RequestBody Attendance abttendance){
        boolean tag=this.abstractService.sava(abttendance);
        if (tag){
            return new CommonsResult(200,"创建考勤成功",abttendance);
        }
        return new CommonsResult(200,"创建考勤失败",null);
    }

}
