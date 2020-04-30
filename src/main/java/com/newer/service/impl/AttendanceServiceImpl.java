package com.newer.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newer.dao.AttendanceDaoMapper;
import com.newer.domain.Attendance;
import com.newer.dto.PageDto;
import com.newer.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    private AttendanceDaoMapper attendancemapper;
    @Override
    public Attendance findAttendancebyid(Integer id) {
      return this.attendancemapper.selectByPrimaryKey(id);
    }


    @Override
    public boolean sava(Attendance attendance) {
        return this.attendancemapper.insertSelective(attendance) > 0 ? true : false;
    }

    @Override
    public boolean delete(Integer id) {
        return this.attendancemapper.deleteByPrimaryKey(id) > 0 ? true : false;
    }

    @Override
    public boolean update(Attendance attendance) {
        return this.attendancemapper.updateByPrimaryKeySelective(attendance)> 0 ? true : false;
    }

    @Override
    public List<Attendance> findAll() {
        return attendancemapper.selectAll();
    }

    @Override
    public PageInfo<Attendance> findAll(Integer page, PageDto pageDto) {
        if(page!=null){
            pageDto.setPage(page);
        }
        PageHelper.startPage(pageDto.getPage(),pageDto.getPageSize());
        List<Attendance> list = attendancemapper.findAll();
        PageInfo<Attendance> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }


}