package com.newer.controller;

import com.github.pagehelper.PageInfo;
import com.newer.domain.Attendance;
import com.newer.dto.PageDto;
import com.newer.service.AttendanceService;
import com.newer.service.impl.AttendanceServiceImpl;
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
    private AttendanceService abstractServiceimpl ;


    //跳转考勤表界面
//    @GetMapping("createTask")
//    public CommonsResult goSave(){
//        return new CommonsResult(200,"跳转创建任务界面","createTask");
//    }




//    //查询记录
//    @GetMapping("pageInfo")
//    public CommonsResult findAll(Integer page, Integer pageSize){
//        System.out.println(page+","+pageSize);
//        PageDto pageDto=new PageDto();
//        pageDto.setPage(page);
//        pageDto.setPageSize(pageSize);
//        PageInfo pageInfo=this. abstractServiceimpl.findAll(pageDto);
//        if(pageInfo!=null){
//            return new CommonsResult(200,"查询成功",pageInfo);
//        }
//        return new CommonsResult(200,"查询失败",null);
//    }

    @GetMapping("pageInfo")
    public CommonsResult findAll(PageDto dto){
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
    public CommonsResult doSave(@RequestBody Attendance abttendance){

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

