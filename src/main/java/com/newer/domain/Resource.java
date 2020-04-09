package com.newer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Create by 何辉
 * 2020/3/29 19:41
 * 资源表
 */
@Table(name = "t_tree_resource")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Resource {
    @Id
    private Integer id;
    private String name;
    private String resourcecode;
    private String url;
    private String description;
    private String icon;
    private Integer pid;
    private Integer seq;
    private String status;
    private String type;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createtime;
}
