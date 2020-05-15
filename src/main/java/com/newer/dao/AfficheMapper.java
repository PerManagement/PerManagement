package com.newer.dao;

import com.newer.domain.Affiche;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import javax.persistence.Column;
import java.util.List;

public interface AfficheMapper extends Mapper<Affiche> {

    @Results(id = "afficheMap",value = {
            @Result(column = "affiche_id",property = "afficheid"),
            @Result(column = "userid", property = "userid"),
            @Result(column = "userid", property = "user.userid"),
            @Result(column = "affiche_content", property = "affichecontent"),
            @Result(column = "realname", property = "user.realname"),
            @Result(column = "description", property = "user.description")
    })
    @Select("select a.*,b.realname,b.description from t_affiche a,t_tree_user b where a.userid=b.userid and b.userid=#{userid}")
    List<Affiche> findAffiches(Integer userid);

    @Results( {
            @Result(column = "affiche_id",property = "afficheid"),
            @Result(column = "userid", property = "userid"),
            @Result(column = "userid", property = "user.userid"),
            @Result(column = "affiche_content", property = "affichecontent"),
            @Result(column = "realname", property = "user.realname"),
            @Result(column = "description", property = "user.description")
    })
    @Select("select a.*,b.realname,b.description from t_affiche a,t_tree_user b where a.userid=b.userid and a.releasetime like sysdate")
    List<Affiche> findAfficheByDate();
}
