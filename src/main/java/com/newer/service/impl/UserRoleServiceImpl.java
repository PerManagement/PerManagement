package com.newer.service.impl;

import com.newer.dao.UserRoleDaoMapper;
import com.newer.domain.UserRole;
import com.newer.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by 何辉
 * 2020/3/29 16:20
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleDaoMapper userRoleDao;
    @Override
    public List<UserRole> getUserRolebyUserId(Integer id) {
        return this.userRoleDao.getUserRolebyUserId(id);
    }
}
