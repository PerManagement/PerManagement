package com.newer.service;

import com.github.pagehelper.PageInfo;
import com.newer.domain.Attendance;
import com.newer.dto.PageDto;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


/**
 * 考勤表模块
 * 周怡珊
 */
public interface AttendanceService  {
    public boolean sava(Attendance attendance);
    public boolean delete(Integer id);
    public boolean update(Attendance attendance);
    public List<Attendance> findAll() ;
    public PageInfo<Attendance> findAll(Integer page, PageDto pageDto);
    public Attendance findAttendancebyid(Integer id);
}
