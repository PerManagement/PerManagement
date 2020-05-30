package com.newer.service.impl;

import com.newer.dao.DepartmentDaoMapper;
import com.newer.dao.UserDaoMapper;
import com.newer.domain.Department;
import com.newer.domain.User;

import com.newer.service.DepartmentService;

import com.newer.dto.UserDto;

import com.newer.service.UserService;
import com.newer.util.CommonsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;


import java.math.BigDecimal;
import java.util.Date;

import java.util.ArrayList;

import java.util.List;

/**
 * Create by 何辉
 * 2020/3/28 21:15
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDaoMapper dao;

    @Autowired
    private DepartmentService departmentService;


    @Autowired
    private DepartmentDaoMapper department;

    @Override
    public User login(String userName) {
        User user = this.dao.login(userName);
        if(user!=null){
            return user;
        }else{
            return null;
        }
    }

    //谢海鸿  05-04 15:05 查询相关主管的下属
    @Override
    public List<User> findExecutor(Integer id) {
        return this.dao.findExecutor(id);
    }

    @Override
    public List<User> findUsers(Integer id,Integer userid) {
        return this.dao.findUsers(id, userid);
    }

    //何辉 ，管理员查询所有为离职员工
    @Override
    public List<User> showUser() {
        List<User> users = this.dao.showUser();
        return users;

    }

    @Override

    public CommonsResult save(User user) {
        user.setRealname(user.getUsername());
        user.setDescription("员工");
        user.setLocked(0);
        user.setCreatetime(new Date());
        user.setDeletestatus(0);
        Department dept=this.departmentService.findDeptById(user.getDeptid());
        if(("人事部").equals(dept.getDeptname())){
            BigDecimal bigDecimal=new BigDecimal(2000);
            user.setBasepay(bigDecimal);
        }
        if(("财务部").equals(dept.getDeptname())){
            BigDecimal bigDecimal=new BigDecimal(2200);
            user.setBasepay(bigDecimal);
        }
        if(("开发部").equals(dept.getDeptname())){
            BigDecimal bigDecimal=new BigDecimal(4000);
            user.setBasepay(bigDecimal);
        }
        if(("运维部").equals(dept.getDeptname())){
            BigDecimal bigDecimal=new BigDecimal(3800);
            user.setBasepay(bigDecimal);
        }
        if(("测试部").equals(dept.getDeptname())){
            BigDecimal bigDecimal=new BigDecimal(3800);
            user.setBasepay(bigDecimal);
        }
        if(("实施部").equals(dept.getDeptname())){
            BigDecimal bigDecimal=new BigDecimal(3800);
            user.setBasepay(bigDecimal);
        }
        int count=this.dao.insertSelective(user);
        return count>0?new CommonsResult(200,"添加员工成功",true):new CommonsResult(500,"添加员工失败",false);
    }

    @Override
    public List<User> findDeptMager(Integer deptid) {
        return this.dao.findDeptMager(deptid);
    }


    public CommonsResult<User> updateLocked(User user){
        int i = this.dao.updateByPrimaryKeySelective(user);
        return i>0?new CommonsResult(200,"操作成功",null):new CommonsResult(500,"未知错误。。。",null);
    }

    @Override
    public List<UserDto> findUserTaskDept() {
        List<UserDto> userTaskDept = this.dao.findUserTaskDept();
        return userTaskDept;
    }

    @Override
    public CommonsResult showUpno(Integer id) {
        ArrayList arrayList=new ArrayList();
        User user = this.dao.selectByPrimaryKey(id);

        Example example=new Example(User.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("description",user.getDescription());
        criteria.andEqualTo("deptid",user.getDeptid());
        List<User> users = this.dao.selectByExample(example);
        arrayList.add(user);
        arrayList.add(users);

        return new CommonsResult(200,"操作成功",arrayList);
    }

    @Override
    public CommonsResult updateUser(User user) {
        if("经理".equals(user.getDescription()) && user.getDeptid()==2){
            user.setDescription("人事经理");
        }
        int i = this.dao.updateByPrimaryKeySelective(user);
        return i>0?new CommonsResult(200,"操作成功",null):new CommonsResult(200,"操作失败",null);
    }

}
