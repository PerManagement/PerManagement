package com.newer.domain;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 功能描述：任务管理试题类
 * 作者：谢海鸿
 * 时间：2020-04-07 21:00
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = " select seq_task_taskid.nextval from dual ")
    public Integer taskid;
    public String taskname;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date begindate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date enddate;
    public String status;
    public Integer userid;
    public Integer sharer;
    public String taskdesc;
    public User user;

    public String getBegindateString(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(this.begindate!=null){
            return sdf.format(this.begindate);
        }
        return null;
    }

    public String getEnddateString(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(this.enddate!=null){
            return sdf.format(this.enddate);
        }
        return null;
    }

}
