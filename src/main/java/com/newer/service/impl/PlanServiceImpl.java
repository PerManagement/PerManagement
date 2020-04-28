package com.newer.service.impl;

import com.newer.dao.PlanMapper;
import com.newer.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 功能描述：任务管理模块业务实现方法
 * 作者：谢海鸿
 * 时间：2020-04-28 21:50
 */
public class PlanServiceImpl implements PlanService {
    @Autowired
    private PlanMapper planMapper;
}
