package com.newer.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newer.dao.AttendanceMapper;
import com.newer.domain.Attendance;
import com.newer.dto.PageDto;
import com.newer.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    private AttendanceMapper attendancemapper;
    @Override
    public Attendance findAttendancebyid(Integer id) {
        return this.attendancemapper.selectByPrimaryKey(id);
    }

    @Override
    public Attendance findAttendanceByUserId(Integer userid) {
        return this.attendancemapper.findAttendanceByUserId(userid);
    }


    @Override
    public int sava(Attendance attendance) {
        int a=this.attendancemapper.insert(attendance);
        if(a>0){
            return a;
        }
        return 0;
    }


    @Override
    public int delete(Integer id){
        int a=this.attendancemapper.deleteByPrimaryKey(id);
        if(a>0){
            return a;
        }
        return 0;
    }
    @Override
    public int  update(Attendance attendance) {
        int a=this.attendancemapper.updateByPrimaryKey(attendance);
        if(a>0){
            return a;
        }
        return 0;
    }


    @Override
    public PageInfo<Attendance> findAll(PageDto dto) {
//        System.out.println(dto.getPage() + "," + dto.getPageSize());
        PageHelper.startPage(dto.getPage(), dto.getPageSize());
        List list = this.attendancemapper.findAll();

//        System.out.println("list=="+list);
        PageInfo pageInfo = new PageInfo<Attendance>(list);
        return pageInfo;

    }
}