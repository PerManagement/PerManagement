package com.newer.controller;

import com.github.pagehelper.PageInfo;
import com.newer.domain.Plan;
import com.newer.dto.PageDto;
import com.newer.service.PlanService;
import com.newer.util.CommonsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述：任务管理模块控制层
 * 作者：谢海鸿
 * 时间：2020-05-01 20:16
 */

@RestController
@RequestMapping("plan")
public class PlanController {

    @Autowired
    private PlanService planService;

    //计划查询
    @GetMapping("findplans")
    public CommonsResult findplans(PageDto dto){
        PageInfo pageInfo=this.planService.findPlan(dto);
        return new CommonsResult(200,"计划分页",pageInfo);
    }
}
