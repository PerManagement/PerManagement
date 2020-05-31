package com.newer.controller;

import com.github.pagehelper.PageInfo;
import com.newer.domain.Wage;
import com.newer.dto.WagePageDto;
import com.newer.service.impl.WageServiceImpl;
import com.newer.util.CommonsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 功能描述：薪资管理模块控制层
 * 作者：龙珊
 * 时间：2020-04-27
 */
@RestController
@RequestMapping("wage")
public class WageController {
    @Autowired
    public WageServiceImpl wageServiceImpl;

    //跳转页面
    @GetMapping("doSave")
    public CommonsResult goSave(){
        return new CommonsResult(200,"跳转发放工资界面","createWage");
    }

    //发放薪资
    @GetMapping("saveWage")
    public CommonsResult doSave(@RequestBody Wage wage){

        int a=this.wageServiceImpl.save(wage);
        if(a>0){
            return  new CommonsResult(200,"发放成功",wage);
        }
        return  new CommonsResult(200,"发放有误",null);
    }

    //发放薪资
    @GetMapping("updateWage")
    public CommonsResult updateWage(@RequestParam Integer userid,Integer wageid){
        System.out.println(userid);
        System.out.println(wageid);
        int a=this.wageServiceImpl.updateWage(userid,wageid);
        if(a>0){
            System.out.println("发放成功");
            return  new CommonsResult(200,"发放成功",a);
        }
        return  new CommonsResult(200,"发放有误",null);
    }

    //发放薪资
    @GetMapping("updateState")
    public CommonsResult updateState(@RequestParam Integer wageid,Integer approrver){
        int a=this.wageServiceImpl.updateState(wageid,approrver);
        if(a>0){
            System.out.println("审批成功");
            return  new CommonsResult(200,"审批成功",a);
        }
        return  new CommonsResult(200,"审批有误",null);
    }

    //发放薪资
    @GetMapping("updateState2")
    public CommonsResult updateState2(Integer wageid,String remark,Integer approrver){
        System.out.println(remark);
        int a=this.wageServiceImpl.updateState2(wageid,remark,approrver);
        if(a>0){
            return  new CommonsResult(200,"驳回成功",a);
        }
        return  new CommonsResult(200,"驳回有误",null);
    }

    //发放薪资
    @GetMapping("updateState3")
    public CommonsResult updateState3(@RequestParam Integer wageid, double netpayroll){
        BigDecimal bd=new BigDecimal(netpayroll);
        int a=this.wageServiceImpl.updateState3(wageid,bd);
        if(a>0){
            return  new CommonsResult(200,"再次申请成功",a);
        }
        return  new CommonsResult(200,"再次申请有误",null);
    }

    //查询记录
    @GetMapping("pageInfoByDate")
    public CommonsResult pageInfoByDate(Integer page, Integer pageSize, String beginDate,String endDate){
        System.out.println(page+","+pageSize);
        System.out.println(beginDate+","+endDate);
        WagePageDto pageDto=new WagePageDto();
        pageDto.setPageSize(pageSize);
        pageDto.setPage(page);
        if(beginDate!=null && !"".equals(beginDate)){
            pageDto.setBeginDate(beginDate);
        }
        if(endDate!=null && !"".equals(endDate)){
            pageDto.setEndDate(endDate);
        }

        PageInfo pageInfo=this.wageServiceImpl.pageInfoByDate(pageDto);
        if(pageInfo!=null){
            return new CommonsResult(200,"查询成功",pageInfo);
        }
        return new CommonsResult(200,"查询失败",null);
    }

    //查询记录
    @GetMapping("pageInfo2")
    public CommonsResult pageInfo2(Integer page, Integer pageSize){
        WagePageDto pageDto=new WagePageDto();
        pageDto.setPageSize(pageSize);
        pageDto.setPage(page);
        PageInfo pageInfo=this.wageServiceImpl.pageInfo2(pageDto);
        if(pageInfo!=null){
            return new CommonsResult(200,"查询成功",pageInfo);
        }
        return new CommonsResult(200,"查询失败",null);
    }


    //查询记录
    @GetMapping("findByUserId")
    public CommonsResult findByUserId(Integer page, Integer pageSize,Integer userId){
        WagePageDto pageDto=new WagePageDto();
        pageDto.setPageSize(pageSize);
        pageDto.setPage(page);
        pageDto.setUserId(userId);
        PageInfo pageInfo=this.wageServiceImpl.findByUserId(pageDto);
        if(pageInfo!=null){
            return new CommonsResult(200,"查询成功",pageInfo);
        }
        return new CommonsResult(200,"查询失败",null);
    }


    //查询记录
    @GetMapping("pageInfo")
    public CommonsResult pageInfo(Integer page, Integer pageSize){
        WagePageDto pageDto=new WagePageDto();
        pageDto.setPageSize(pageSize);
        pageDto.setPage(page);
        PageInfo pageInfo=this.wageServiceImpl.pageInfo(pageDto);
        if(pageInfo!=null){
            return new CommonsResult(200,"查询成功",pageInfo);
        }
        return new CommonsResult(200,"查询失败",null);
    }

}
