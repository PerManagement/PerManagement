package com.newer.controller;

import com.github.pagehelper.PageInfo;
import com.newer.domain.Plan;
import com.newer.domain.Task;
import com.newer.dto.PageDto;
import com.newer.dto.PlanDto;
import com.newer.service.PlanService;
import com.newer.util.CommonsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    //计划查询 PlanDto planDto
    @GetMapping("findplans")
    public CommonsResult findplans(PlanDto planDto){
            PageInfo pageInfo=this.planService.findPlan(planDto);
        return new CommonsResult(200,"计划分页",pageInfo);
    }

    @GetMapping("findPlanById")
    public CommonsResult findPlanById(PlanDto planDto){
        PageInfo pageInfo=this.planService.findPlan(planDto);
        return new CommonsResult(200,"根据taskid查询计划",pageInfo);
    }
    //修改任务 2020-05-02 23:01
    @PostMapping("update")
    public CommonsResult update(@RequestBody Plan plan){
        boolean tag=this.planService.update(plan);
        if (tag){
            return new CommonsResult(200,"修改计划成功",plan);
        }
        return new CommonsResult(500,"修改计划失败",null);
    }

    @PostMapping("save")
    public CommonsResult save(@RequestBody Plan plan){
        boolean tag=this.planService.save(plan);
        if (tag){
            return new CommonsResult(200,"制定计划成功",plan);
        }
        return new CommonsResult(500,"制定计划失败",null);
    }
}
