package com.newer.service;

import com.newer.domain.RoleResource;

import java.util.List;

/**
 * Create by 何辉
 * 2020/3/29 19:33
 */
public interface RoleResourceService {
    public List<RoleResource> findByRoleId(Integer id);
}
