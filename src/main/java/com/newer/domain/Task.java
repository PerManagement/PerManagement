package com.newer.domain;


import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 功能描述：任务管理试题类
 * 作者：谢海鸿
 * 时间：2020-04-07 21:00
 */
@Table(name ="t_pro_task")
@Getter
@Setter
public class Task {
    @Id
    @Column(name="task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select seq_t_pro_task_id.nextval from dual")
    public Integer taskid;
    @Column(name="task_name")
    public String taskname;
    @Column(name="begin_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date begindate;
    @Column(name="end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date enddate;
    public String status;
    public Double bonus;
    @Column(name="user_id")
    public Integer userid;
    @Column(name="task_desc")
    public String taskdesc;

}
