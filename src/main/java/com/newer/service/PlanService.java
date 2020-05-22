package com.newer.service;


import com.github.pagehelper.PageInfo;
import com.newer.domain.Plan;
import com.newer.dto.PageDto;
import com.newer.dto.PlanDto;

import java.util.List;

/**
 * 功能描述：任务模块的计划
 * 作者：谢海鸿
 * 时间：2020-04-28 21:37
 */
public interface PlanService {

    public boolean save(Plan plan);
    public boolean update(Plan plan);
    public PageInfo<Plan> findPlan(PlanDto planDto);
    public PageInfo<Plan> findPlanById(PlanDto planDto);
//    public List<Plan> findPlan(Integer taskid);

}
