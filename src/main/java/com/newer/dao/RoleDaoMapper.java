package com.newer.dao;

import com.newer.domain.Role;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Create by 何辉
 * 2020/3/29 18:41
 */
public interface RoleDaoMapper extends Mapper<Role> {
    @Select("select * from t_tree_role where id=#{roleid}")
    @Results({
            @Result(column = "id",property = "roleResources",many = @Many(select="com.newer.dao.RoleResourceDaoMapper.findByRoleId",fetchType= FetchType.EAGER))
    })
    public List<Role> findByKey(Integer roleid);
}
