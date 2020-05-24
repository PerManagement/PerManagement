package com.newer.dao;

import com.newer.domain.Evection;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface EvectionDaoMapper extends Mapper <Evection> {
    @Select("select a.*,b.username from t_evection a,t_tree_user b where a.userid=b.userid")
    @Results(id="EvectionMap",value={
            @Result(column = "username",property = "user.username")
    })
    public List<Evection> findAal();

    @Select("select a.*,b.username from t_evection a,t_tree_user b where b.userid=a.userid and evectionstate is null")
    public List<Evection> findAal2();
    @Update("update t_evection  set evectionstate = '同意' where evectionid=#{evectionid}")
    int updateState(Integer evectionid);
    @Update("update t_evection set evectionstate='同意',userid=#{arg0} where evectionid=#{arg1}")
    int updatelhl(Integer  arg0,Integer arg1);
    @Update("update t_evection set evectionstate = '驳回' where evectionid=#{evectionid}")
    int updateState2(Integer evectionid);
}
