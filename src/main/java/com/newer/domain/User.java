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
 * 2020/3/28 20:58
 * 用户表
 */
@Table(name = "t_tree_user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User{
    @Id
    private Integer id;
    private String username;
    private String password;
    private String realname;
    private String salt;
    private String description;
    private Integer locked;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createtime;
    private Integer deletestatus;
    private String sex;
    private Integer age;
    private String education;
    private String telephone;
    private String email;
    private String province;
    private String city;
    private String area;
    private String address;
    private Integer upno;
    private Integer deptno;
    private List<UserRole> userRoles;

}
