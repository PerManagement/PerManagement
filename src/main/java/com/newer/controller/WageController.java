package com.newer.controller;

import com.github.pagehelper.PageInfo;
import com.newer.domain.Wage;
import com.newer.dto.WagePageDto;
import com.newer.service.impl.WageServiceImpl;
import com.newer.util.CommonsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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
    @GetMapping("createWage")
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

    //查询记录
    @GetMapping("pageInfo")
    public CommonsResult pageInfo(Integer page, Integer pageSize, String beginDate,String endDate){
        System.out.println(page+","+pageSize);
        System.out.println(beginDate+","+endDate);
        WagePageDto pageDto=new WagePageDto();
        pageDto.setPage(page);
        pageDto.setPageSize(pageSize);
        PageInfo pageInfo=this.wageServiceImpl.pageInfo(pageDto);
        if(pageInfo!=null){
            return new CommonsResult(200,"查询成功",pageInfo);
        }
        return new CommonsResult(200,"查询失败",null);
    }



}
