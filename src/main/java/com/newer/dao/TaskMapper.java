package com.newer.dao;

import com.newer.domain.Task;
import com.newer.dto.TaskDto;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 功能描述：任务管理模块Dao层
 * 作者：谢海鸿
 * 时间：2020-04-07 21:05
 */
public interface TaskMapper extends Mapper<Task> {

    //谢海鸿 05-05 17:00
    @Results(id="taskMap",value = {
            @Result(column = "userid",property = "user.userid"),
            @Result(column = "username",property = "user.username"),
            @Result(column = "realname",property = "user.realname"),
            @Result(column = "upno",property = "user.upno"),
    })
//    @Select("select a.*,b.username,b.realname,b.upno from t_pro_task a,t_tree_user b where a.userid=b.userid")
    @SelectProvider(method = "findTask",type = TaskProvider.class )
    List<Task> findTask(@Param("taskDto")TaskDto taskDto);

    @ResultMap("taskMap")
    @Select("select a.*,b.username,b.realname,b.upno from t_pro_task a,t_tree_user b " +
            "where a.userid=b.userid and a.userid=#{userid}")
    List<Task> findTaskByUserId(Integer userid);

    @Results(id="TMap",value = {
            @Result(column = "userid",property = "userid"),
            @Result(column = "username",property = "user.username"),
            @Result(column = "realname",property = "user.realname"),
            @Result(column = "upno",property = "user.upno"),
    })
    @Select("select * from t_pro_task a,t_tree_user b where a.userid=b.userid and status='未交接' and sharer=#{sharer}")
    List<Task> findAssociateWith(Integer sharer);

    @Select("select * from t_pro_task a,t_tree_user b where a.userid=b.userid and description='员工' and a.userid=#{userId}")
    List<Task> findTaskByUserid(Integer userId);

    @ResultMap("TMap")
    @Select("select * from t_pro_task a,t_tree_user b where a.userid=b.userid and description='员工' and status='已完成' and upno=#{id}")
    List<Task> checkTask(Integer id);
//    List<Task> findTaskByUserId(@Param("taskByUserIdDto")TaskByUserIdDto taskByUserIdDto);

//    @Results({
//            @Result(column = "taskid",property = "plan",many = @Many(select = "com.newer.dao.PlanMapper.findPlan",fetchType= FetchType.EAGER))
//    })
//    @Select("select * from t_pro_task where taskid=#{taskid}")
//    Task findPlan(Integer taskid);
}