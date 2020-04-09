package com.newer.service;

import com.newer.domain.Role;

import java.util.List;

/**
 * Create by 何辉
 * 2020/3/29 19:20
 */
public interface RoleService {
    public List<Role> findByKey(Integer roleid);
}
