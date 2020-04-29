package com.newer.service.impl;


import com.github.pagehelper.PageInfo;
import com.newer.dao.AttendanceDaoMapper;
import com.newer.domain.Attendance;
import com.newer.service.AttendanceService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceServiceImpl  implements AttendanceService{
    @Autowired
    private AttendanceDaoMapper  attendanceDaoMapper;
    @Override
    public boolean sava(Attendance attendance) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean update(Attendance attendance) {
        return false;
    }

    @Override
    public PageInfo<Attendance> findAttendance(Integer page, Integer pageSize) {
        return null;
    }

    @Override
    public Attendance findAttendancebyid(Integer id) {
        return null;
    }

    @Override
    public int deleteByPrimaryKey(Object o) {
        return 0;
    }

    @Override
    public int delete(Attendance attendance) {
        return 0;
    }

    @Override
    public int insert(Attendance attendance) {
        return 0;
    }

    @Override
    public int insertSelective(Attendance attendance) {
        return 0;
    }

    @Override
    public boolean existsWithPrimaryKey(Object o) {
        return false;
    }

    @Override
    public List<Attendance> selectAll() {
        return null;
    }

    @Override
    public Attendance selectByPrimaryKey(Object o) {
        return null;
    }

    @Override
    public int selectCount(Attendance attendance) {
        return 0;
    }

    @Override
    public List<Attendance> select(Attendance attendance) {
        return null;
    }

    @Override
    public Attendance selectOne(Attendance attendance) {
        return null;
    }

    @Override
    public int updateByPrimaryKey(Attendance attendance) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Attendance attendance) {
        return 0;
    }

    @Override
    public int deleteByExample(Object o) {
        return 0;
    }

    @Override
    public List<Attendance> selectByExample(Object o) {
        return null;
    }

    @Override
    public int selectCountByExample(Object o) {
        return 0;
    }

    @Override
    public Attendance selectOneByExample(Object o) {
        return null;
    }

    @Override
    public int updateByExample(Attendance attendance, Object o) {
        return 0;
    }

    @Override
    public int updateByExampleSelective(Attendance attendance, Object o) {
        return 0;
    }

    @Override
    public List<Attendance> selectByExampleAndRowBounds(Object o, RowBounds rowBounds) {
        return null;
    }

    @Override
    public List<Attendance> selectByRowBounds(Attendance attendance, RowBounds rowBounds) {
        return null;
    }


}
