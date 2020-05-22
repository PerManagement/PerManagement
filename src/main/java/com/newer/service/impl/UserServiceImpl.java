package com.newer.service.impl;

import com.newer.dao.UserDaoMapper;
import com.newer.domain.User;
import com.newer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by 何辉
 * 2020/3/28 21:15
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDaoMapper dao;
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
        return this.dao.findUsers(id,userid);
    }
}
