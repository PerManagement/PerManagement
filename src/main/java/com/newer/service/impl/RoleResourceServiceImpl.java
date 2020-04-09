package com.newer.service.impl;

import com.newer.dao.RoleResourceDaoMapper;
import com.newer.domain.RoleResource;
import com.newer.service.RoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by 何辉
 * 2020/3/29 19:36
 */
@Service
public class RoleResourceServiceImpl implements RoleResourceService {
    @Autowired
    private RoleResourceDaoMapper daoMapper;
    @Override
    public List<RoleResource> findByRoleId(Integer id) {
        return this.daoMapper.findByRoleId(id);
    }
}
