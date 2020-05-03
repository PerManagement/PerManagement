package com.newer.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newer.dao.PlanMapper;
import com.newer.domain.Plan;
import com.newer.domain.Task;
import com.newer.dto.PageDto;
import com.newer.service.PlanService;
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
    public boolean update(Plan plan) {
        return this.planMapper.updateByPrimaryKeySelective(plan)>0?true:false;
    }

    @Override
    public PageInfo<Plan> findPlan(PageDto dto) {

        PageHelper.startPage(dto.getPage(), dto.getPageSize());
        List list=this.planMapper.selectAll();
        PageInfo<Plan> pageInfo=new PageInfo<Plan>(list);
        return pageInfo;
    }
}
