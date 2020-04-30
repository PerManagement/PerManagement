package com.newer.dao;

import com.newer.domain.Attendance;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AttendanceDaoMapper extends Mapper <Attendance> {

    @Select("select a.*,b.name from t_eave a,users b where a.userid=b.id")
    public List<Attendance> findAll();
}
