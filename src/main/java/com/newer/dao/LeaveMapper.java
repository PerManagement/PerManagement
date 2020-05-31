package com.newer.dao;

import com.newer.domain.Leave;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface LeaveMapper extends Mapper<Leave> {

    @Results(id="LeaveMap",value={
            @Result(column = "leaveid",property = "leaveid"),
            @Result(column = "userid",property = "userid"),
            @Result(column = "username",property = "user.username"),
            @Result(column = "upno",property = "user.upno"),
            @Result(column = "sjide",property = "user.sjide"),
            @Result(column = "leavereason",property = "leavereason"),
            @Result(column = "leavetime",property = "leavetime"),
            @Result(column = "stopleave",property = "stopleave"),
            @Result(column = "approval",property = "approval"),
            @Result(column = "leavestate",property = "leavestate"),
            @Result(column = "finall",property = "final"),
            @Result(column = "approver",property = "approver"),
            @Result(column = "leavestate",property = "leavestate"),
            @Result(column = "remark",property = "remark"),
            @Result(column = "remark",property = "remarks"),
            @Result(column = "duration",property = "duration")


    })
    @Select("select a.*,b.username from t_leave a,t_tree_user b where a.userid=b.userid")
    public List<Leave> findlhl();
    @Select("select a.*,b.username from t_leave a,t_tree_user b where a.approval=b.upno and b.userid=a.userid and leavestate is null")
    List<Leave> pageInfo();
    @Select("select a.*,b.username from t_leave a,t_tree_user b where b.userid=a.userid and final is null")
    List<Leave> pageInfo2();
    @Update("update t_leave set final='同意',userid=#{arg0} where leaveid=#{arg1}")
    int updatelhl(Integer  arg0,Integer arg1);

    @Update("update t_leave set leavestate = '同意' where leaveid=#{leaveid}")
    int updateState(Integer leaveid);

    @Update("update t_leave set leavestate = '驳回' where leaveid=#{leaveid}")
    int updateState2(Integer leaveid);

//    @Update("update t_leave set final = '驳回' and approver=#{approver} where leaveid=#{leaveid}")
//    int updateState3(Integer leaveid,Integer approver);

}
