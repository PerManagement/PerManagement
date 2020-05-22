package com.newer.dao;

import com.newer.domain.Plan;
import com.newer.dto.PlanDto;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 功能描述：任务管理模块Dao层
 * 作者：谢海鸿
 * 时间：2020-04-28 21:38
 */
public interface PlanMapper extends Mapper<Plan> {

    //谢海鸿 05-05 17:25

    @Select("select * from t_pro_plan  where taskid = #{taskid}")
    List<Plan> findPlan(Integer taskid);
}
