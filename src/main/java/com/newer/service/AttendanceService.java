package com.newer.service;

import com.github.pagehelper.PageInfo;
import com.newer.domain.Attendance;
import tk.mybatis.mapper.common.Mapper;

/**
 * 考勤表模块
 * 周怡珊
 */
public interface AttendanceService extends Mapper <Attendance> {
    public boolean sava(Attendance attendance);
    public boolean delete(Integer id);
    public boolean update(Attendance attendance);

    public Attendance findAttendancebyid(Integer id);
}
