package com.newer.service;

import com.newer.domain.UserRole;

import java.util.List;

/**
 * Create by 何辉
 * 2020/3/29 16:20
 */
public interface UserRoleService {
    public List<UserRole> getUserRolebyUserId(Integer id);
}
