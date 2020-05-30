package com.newer.service;

import com.newer.domain.User;

import com.newer.dto.UserDto;

import com.newer.util.CommonsResult;

import java.util.List;

/**
 * Create by 何辉
 * 2020/3/28 21:15
 */
public interface UserService {
    public User login(String userName);

    //谢海鸿  05-04 15:03 查询相关主管的下属
    public List<User> findExecutor(Integer id);

    public List<User> findUsers(Integer id,Integer userid);


    //管理员查询所有员工
    public List<User> showUser();


    public CommonsResult save(User user);

    public List<User> findDeptMager(Integer deptid);//查询部门经理


    public CommonsResult<User> updateLocked(User user);

    public List<UserDto> findUserTaskDept();


    public CommonsResult showUpno(Integer id);

    public CommonsResult updateUser(User user);

}
