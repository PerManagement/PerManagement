package com.newer.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 公告信息类
 * 2020-05-02
 * 陈良吉
 */
@Table(name = "t_affiche")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Affiche {
    @Id
    @Column(name = "affiche_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select seq_affiche_affche_id.nextval from dual")
    public Integer afficheid;
    public Integer userid;
    public String title;
    @Column(name = "affiche_content")
    public String affichecontent;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date releasetime;

    public User user;

}
