package com.newer.service;

import com.newer.domain.User;

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

}
