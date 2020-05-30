package com.newer.dao;

import com.newer.domain.RoleResource;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Create by 何辉
 * 2020/3/29 19:11
 */
public interface RoleResourceDaoMapper extends Mapper<RoleResource> {
    @Select("select * from t_tree_role_resource where roleid=#{id}")
    @Results({
       @Result(column = "resourceid",property = "resources",many = @Many(select = "com.newer.dao.ResourceDaoMapper.selectByPrimaryKey",fetchType= FetchType.EAGER))
    })
    public List<RoleResource> findByRoleId(Integer id);

}
