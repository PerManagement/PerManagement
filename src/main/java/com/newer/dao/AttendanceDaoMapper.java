package com.newer.dao;

import com.newer.domain.Attendance;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AttendanceDaoMapper extends Mapper <Attendance> {

    @Select("select a.*,b.realname from t_attendance a,t_tree_user b where a.userid=b.userid")
    @Results(id="AttendanceMap",value={
            @Result(column = "attendanceid",property = "attendanceid"),
            @Result(column = "userid",property = "userid"),
            @Result(column = "username",property = "user.username"),
            @Result(column = "morninghours",property = "morninghours"),
            @Result(column = "afternoonclosingtime",property = "afternoonclosingtime"),
            @Result(column = "clockinstate",property = "clockinstate"),
            @Result(column = "clockoutstate",property = "clockoutstate"),
    })
    public List<Attendance> findAll();
}
