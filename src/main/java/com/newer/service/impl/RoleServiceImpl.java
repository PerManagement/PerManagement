package com.newer.service.impl;

import com.newer.dao.RoleDaoMapper;
import com.newer.domain.Role;
import com.newer.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by 何辉
 * 2020/3/29 19:20
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDaoMapper daoMapper;
    @Override
    public List<Role> findByKey(Integer roleid) {
        return daoMapper.findByKey(roleid);
    }
}
