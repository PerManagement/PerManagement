package com.newer.service;

import com.github.pagehelper.PageInfo;
import com.newer.domain.Attendance;
import com.newer.dto.PageDto;

public interface AttendanceService {
    public  int sava(Attendance attendance);
    public int delete(Integer id);
    public int update(Attendance attendance);
    public PageInfo<Attendance> findAll(PageDto dto);
    public Attendance findAttendancebyid(Integer id);
    public Attendance findAttendanceByUserId(Integer userid);
}
