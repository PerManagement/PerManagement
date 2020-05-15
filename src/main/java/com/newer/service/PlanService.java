package com.newer.service;


import com.github.pagehelper.PageInfo;
import com.newer.domain.Plan;
import com.newer.dto.PageDto;

/**
 * 功能描述：任务模块的计划
 * 作者：谢海鸿
 * 时间：2020-04-28 21:37
 */
public interface PlanService {

    public boolean update(Plan plan);
    public PageInfo<Plan> findPlan(PageDto dto);

}
