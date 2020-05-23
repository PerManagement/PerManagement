package com.newer.dao;

import com.newer.domain.Dimission;
import com.newer.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import javax.persistence.Column;
import java.util.List;

/**
 * Create by 何辉
 * 2020/5/21 14:06
 */
public interface DimissionDaoMapper extends Mapper<Dimission> {

    @Results({
            @Result(column = "username",property = "user.username"),
            @Result(column = "dmission_id",property = "dmissionId"),
            @Result(column = "dim_date",property = "dimDate"),
            @Result(column = "exist_task",property = "existTask"),

    })
    @SelectProvider(method = "showDimission",type = DimissionProvider.class)
    public List<Dimission> showDimission(@Param("user") User user);
}
