package com.newer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * Create by 何辉
 * 2020/3/29 19:09
 * 角色资源表
 */
@Table(name = "t_tree_role_resource")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleResource {
    @Id
    private Integer id;
    private Integer roleid;
    private Integer resourceid;
    private List<Resource> resources;
}
