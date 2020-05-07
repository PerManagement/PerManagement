package com.newer.dao;

import com.newer.domain.User;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import tk.mybatis.mapper.common.Mapper;

/**
 * Create by 何辉
 * 2020/3/28 21:13
 */
public interface UserDaoMapper extends Mapper<User> {
    @Select("select * from t_tree_user where username=#{username}")
    @Results(
            @Result(column = "id",property = "userRoles",many=@Many(select="com.newer.dao.UserRoleDaoMapper.getUserRolebyUserId",fetchType= FetchType.EAGER))
    )
    public User login(String username);
}
