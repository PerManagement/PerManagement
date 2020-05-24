package com.newer.dao;

import com.newer.domain.Resource;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Create by 何辉
 * 2020/3/29 19:46
 */
public interface ResourceDaoMapper extends Mapper<Resource> {

    //tree树形结构
    @Select("select * from t_tree_resource start with id=#{id} connect by prior id = pid")
    List<Resource> tree(Integer id);
}
