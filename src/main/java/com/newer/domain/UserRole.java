package com.newer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * Create by 何辉
 * 2020/3/29 15:37
 * 用户角色表
 */
@Table(name = "t_tree_user_role")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = " select t_tree_user_role_id.nextval from  dual ")
    private Integer id;
    private Integer userid;
    private Integer roleid;
    private List<Role> roles;
}
