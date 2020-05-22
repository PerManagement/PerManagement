package com.newer.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newer.dao.PlanMapper;
import com.newer.domain.Plan;
import com.newer.dto.PlanDto;
import com.newer.service.PlanService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能描述：任务模块的计划
 * 作者：谢海鸿
 * 时间：2020-04-28 21:50
 */

@Service
public class PlanServiceImpl implements PlanService {
    @Autowired
    private PlanMapper planMapper;

    @Override
    public boolean save(Plan plan) {
        plan.setState("未完成");
        return this.planMapper.insertSelective(plan)>0?true:false;
    }

    @Override
    public boolean update(Plan plan) {
        return this.planMapper.updateByPrimaryKeySelective(plan)>0?true:false;
    }

    @Override
    public PageInfo<Plan> findPlanById(@Param("planDto")PlanDto planDto) {
        PageHelper.startPage(planDto.getPage(), planDto.getPageSize());
        List<Plan> list=this.planMapper.selectAll();
        PageInfo<Plan> pageInfo=new PageInfo<Plan>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<Plan> findPlan(@Param("planDto")PlanDto planDto) {
        PageHelper.startPage(planDto.getPage(), planDto.getPageSize());
        List<Plan> list=this.planMapper.findPlan(planDto.getTaskid());
        PageInfo<Plan> pageInfo=new PageInfo<Plan>(list);
        return pageInfo;
    }

//    @Override
//    public List<Plan> findPlan(Integer taskid) {
//        List list= this.planMapper.findPlan(taskid);
//        return list;
//    }
}
