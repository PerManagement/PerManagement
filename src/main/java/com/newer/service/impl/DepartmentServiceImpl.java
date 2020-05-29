package com.newer.service.impl;

import com.newer.dao.DepartmentMapper;
import com.newer.domain.Department;
import com.newer.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 谢海鸿
 * @Date 2020-05-28 14:47
 **/
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public Department findDeptById(Integer deptid) {
        return this.departmentMapper.selectByPrimaryKey(deptid);
    }
}
