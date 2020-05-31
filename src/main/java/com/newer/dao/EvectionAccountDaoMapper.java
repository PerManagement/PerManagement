package com.newer.dao;

import com.newer.domain.EvectionAccount;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface EvectionAccountDaoMapper extends Mapper <EvectionAccount> {
    @Results(id="EvectionAccountMap",value={
            @Result(column = "username",property = "user.username"),
            @Result(column = "evection",property = "evection.evectiondate"),
            @Result(column = "evection",property = "evection.stopevection")


    })
    @Select("select a.*,b.t_username,d.evectiondate,d.stopevection from t_evectionaccount a,t_tree_user b,t_evection d where a.evectionid=d.evectionid a.userid=b.userid")
    public List<EvectionAccount> findAal();

}
