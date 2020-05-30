package com.newer.dao;

import com.newer.domain.Overtim;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OvertimMapper extends Mapper<Overtim> {

    @Results(id = "OvertimMap",value = {
            @Result(column = "overtimid", property = "overtimid"),
            @Result(column = "userid", property = "userid"),
            @Result(column = "userid", property = "user.userid"),
            @Result(column = "stopovertime", property = "stopovertime"),
            @Result(column = "overtimstate", property = "overtimstate"),
            @Result(column = "overtimreason", property = "overtimreason"),
            @Result(column = "approver", property = "approver"),
            @Result(column = "remark", property = "remark"),
            @Result(column = "overtimtype", property = "overtimtype"),
            @Result(column = "overtimedated", property = "overtimedated"),
            @Result(column = "realname", property = "user.realname"),
    })
    @Select("select b.*,a.realname from t_tree_user a,t_overtim b " +
            " where a.userid=b.userid and b.overtimtype='待审批' " +
            " and a.upno=#{upno} order by b.overtimedated desc")
    List<Overtim> findOvertimByUpno(Integer upno);

    @ResultMap("OvertimMap")
    @Select("select b.*,a.realname from t_tree_user a,t_overtim b " +
            " where a.userid=b.userid and b.overtimid=#{overtimid}")
    Overtim findOvertimByKey(Integer overtimid);

    @ResultMap("OvertimMap")
    @Select("select b.*,a.realname from t_tree_user a,t_overtim b " +
            " where a.userid=b.userid and b.userid=#{userid}")
    List<Overtim> findOvertimByUserId(Integer userid);
}
