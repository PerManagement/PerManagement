
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

    @Results(id = "afficheMap",value = {
            @Result(column = "affiche_id",property = "afficheid"),
            @Result(column = "userid", property = "userid"),
            @Result(column = "userid", property = "user.userid"),
            @Result(column = "affiche_content", property = "affichecontent"),
            @Result(column = "realname", property = "user.realname"),
            @Result(column = "description", property = "user.description")
    })
    @Select("select a.*,b.realname,b.description from t_affiche a,t_tree_user b where a.userid=b.userid and b.userid=#{userid} order by a.releasetime desc")
    List<Affiche> findAffiches(Integer userid);

    @Results( {
            @Result(column = "affiche_id",property = "afficheid"),
            @Result(column = "userid", property = "userid"),
            @Result(column = "userid", property = "user.userid"),
            @Result(column = "affiche_content", property = "affichecontent"),
            @Result(column = "realname", property = "user.realname"),
            @Result(column = "description", property = "user.description")
    })
    @Select("select a.*,b.realname,b.description,rownum from t_affiche a,t_tree_user b where a.userid=b.userid and a.releasetime like to_date('2020-04-28','yyyy-mm-dd') and rownum=1 order by a.releasetime desc")
    Affiche findAfficheByDate();
}

