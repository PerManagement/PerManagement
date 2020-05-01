package com.newer.domain;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 功能描述：任务管理试题类
 * 作者：谢海鸿
 * 时间：2020-04-07 21:00
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name ="t_pro_task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select seq_task_taskid.nextval from dual")
    public Integer taskid;
    public String taskname;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date begindate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date enddate;
    public String status;
    public Integer userid;
    public String taskdesc;

}
