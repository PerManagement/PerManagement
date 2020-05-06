package com.newer.dao;

import com.newer.domain.Leave;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface LeaveMapper extends Mapper<Leave> {

    @Results(id="LeaveMap",value={
            @Result(column = "leaveid",property = "leaveid"),
            @Result(column = "userid",property = "userid"),
            @Result(column = "username",property = "user.username"),
            @Result(column = "leavereason",property = "leavereason"),
            @Result(column = "leavetime",property = "leavetime"),
            @Result(column = "stopleave",property = "stopleave"),
            @Result(column = "approval",property = "approval"),
            @Result(column = "leavestate",property = "leavestate"),
            @Result(column = "finall",property = "final"),
            @Result(column = "approver",property = "approver "),
            @Result(column = "leavestate",property = "leavestate"),
            @Result(column = "remark",property = "remark")

    })
    @Select("select a.*,b.username from t_leave a,t_tree_user b where a.userid=b.userid")
    public List<Leave> findlhl();



}
