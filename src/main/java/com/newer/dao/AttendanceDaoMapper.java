package com.newer.dao;

import com.newer.domain.Attendance;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AttendanceDaoMapper extends Mapper <Attendance> {

    @Select("select a.*,d.username,b.deptname from t_attendance a,t_department b,t_tree_user d where a.deptid=b.deptid and d.userid=a.userid")
    @Results(id="AttendanceMap",value={
            @Result(column = "attendanceid",property = "attendanceid"),
            @Result(column = "userid",property = "userid"),
            @Result(column = "username",property = "user.username"),
            @Result(column = "deptid",property = "deptid"),
            @Result(column = "deptname",property = "department.deptname"),
            @Result(column = "morninghours",property = "morninghours"),
            @Result(column = "afternoonclosingtime",property = "afternoonclosingtime"),
            @Result(column = "recorddate",property = "recorddate"),
            @Result(column = "remark ",property = "remark")
    })
    public List<Attendance> findAll();
}
