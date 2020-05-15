package com.newer.dao;

import com.newer.domain.Affiche;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import javax.persistence.Column;
import java.util.List;

public interface AfficheMapper extends Mapper<Affiche> {

    @Results({
            @Result(column = "userid",property = "userid"),
        @Result(column = "username",property = "user.username")
            })
    @Select("select a.*,b.username from t_affiche a,t_tree_user b where a.userid=b.userid and b.userid=#{userid}")
    public List<Affiche> findAffiches(Integer userid);
}
