package com.newer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * Create by 何辉
 * 2020/3/29 18:38
 * 角色表
 */
@Table(name = "t_tree_role")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role {
    @Id
    private Integer id;
    private String rolename;
    private String rolecode;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createtime;
    private Integer deletestatus;
    private Integer isdefault;
    private List<RoleResource> roleResources;
}
