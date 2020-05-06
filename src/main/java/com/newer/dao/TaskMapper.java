package com.newer.dao;

import com.newer.domain.Task;
import com.newer.dto.TaskDto;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 功能描述：任务管理模块Dao层
 * 作者：谢海鸿
 * 时间：2020-04-07 21:05
 */
public interface TaskMapper extends Mapper<Task> {

    //谢海鸿 05-05 17:00
    @Results({
            @Result(column = "username",property = "user.username"),
            @Result(column = "realname",property = "user.realname"),
            @Result(column = "upno",property = "user.upno"),
    })
//    @Select("select a.*,b.username,b.realname,b.upno from t_pro_task a,t_tree_user b where a.userid=b.userid")
    @SelectProvider(method = "findTask",type = TaskProvider.class )
    List<Task> findTask(@Param("taskDto")TaskDto taskDto);
}
