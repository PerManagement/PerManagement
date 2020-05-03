package com.newer.dao;

import com.newer.domain.Affiche;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AfficheMapper extends Mapper<Affiche> {
    @Results(id = "AfficheMap", value = {
            @Result(column = "userid", property = "user.id", one = @One(select = "com.newer.dao.UserDaoMapper.findUserById"))
    })
    @Select("select * from t_affiche where userid=#{userid}")
    public List<Affiche> findAffiches(Integer userid);
}
