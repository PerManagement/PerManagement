package com.newer.dao;

import com.newer.domain.Plan;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 功能描述：任务管理模块Dao层
 * 作者：谢海鸿
 * 时间：2020-04-28 21:38
 */
public interface PlanMapper extends Mapper<Plan> {

    //谢海鸿 05-05 17:25
    @Results({
            @Result(column = "taskname",property = "task.taskname"),
    })
    @Select("select a.*,b.taskname from t_pro_plan a,t_pro_task b where a.taskid=b.taskid")
    List<Plan> findPlan();
}
