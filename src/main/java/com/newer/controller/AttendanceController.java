package com.newer.controller;

import com.github.pagehelper.PageInfo;
import com.newer.domain.Attendance;
import com.newer.dto.PageDto;
import com.newer.service.AttendanceService;
import com.newer.service.impl.AttendanceServiceImpl;
import com.newer.util.CommonsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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


    //创建考勤表
    @PostMapping("save")
    public CommonsResult doSave(@RequestBody Attendance abttendance){
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1=new Date();
        abttendance.setMorninghours(date1);
        Date date2 =null;
        String str1=sdf1.format(date1)+" 09:00:00";
        try {
            date2=sdf2.parse(str1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //after开始时间在结束时间之后true
        if(abttendance.getMorninghours().after(date2)){
            abttendance.setClockinstate("迟到");
        }else{
            abttendance.setClockinstate("正常");
        }
//        System.out.println("abttendance="+abttendance);
        int a=this.abstractServiceimpl.sava(abttendance);
        if(a>0){
            return  new CommonsResult(200,"上班打卡成功",abttendance);
        }
        return  new CommonsResult(200,"上班打卡失败，请再次尝试",null);
    }
    //修改考勤表
    @PostMapping("update")
    public CommonsResult doUpdate(@RequestBody Attendance abttendance){
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date1=new Date();
        abttendance.setAfternoonclosingtime(date1);
        Date date2 =null;

        String str1=sdf1.format(date1)+" 17:00:00";
        try {
            date2=sdf2.parse(str1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //before开始时间在结束时间之前true
        if(abttendance.getAfternoonclosingtime().before(date2)){
            abttendance.setClockoutstate("早退");
        }else{
            abttendance.setClockoutstate("正常");
        }
        int a=this.abstractServiceimpl.update(abttendance);
//        System.out.println("aaaaaaaaaaaaaaaaa");
        if(a>0){
            return  new CommonsResult(200,"下班打卡成功",abttendance);
        }
        return  new CommonsResult(500,"下班打卡失败，请再次尝试",null);
    }

    @GetMapping("findAttendanceByUserId")
    public CommonsResult findAttendanceByUserId(Integer userid){

        Attendance attendance=this.abstractServiceimpl.findAttendanceByUserId(userid);

        if(attendance!=null){
            return  new CommonsResult(200,"今日已上班打卡",attendance);
        }
        return  new CommonsResult(500,"今日还未上班打卡，请打卡",null);
    }

}

