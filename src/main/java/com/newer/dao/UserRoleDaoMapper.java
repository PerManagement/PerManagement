package com.newer.dao;

import com.newer.domain.UserRole;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Create by 何辉
 * 2020/3/29 16:13
 */
public interface UserRoleDaoMapper extends Mapper<UserRole> {
    @Select("select * from t_tree_user_role where userid=#{id}")
    @Results(
            {
                    @Result(column = "roleid",property = "roles",many = @Many(select = "com.newer.dao.RoleDaoMapper.findByKey",fetchType= FetchType.EAGER))
            }
    )
    public List<UserRole> getUserRolebyUserId(Integer id);
}
