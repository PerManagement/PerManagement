package com.newer.domain;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 功能描述：任务管理试题类
 * 作者：谢海鸿
 * 时间：2020-04-07 21:00
 */



@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="t_pro_task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = " select seq_task_taskid.nextval from dual ")
    private Integer taskid;
    private String taskname;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date begindate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date enddate;
    private String status;
    private Integer userid;
    private Integer sharer;
    private String taskdesc;
    private String reason;
    private User user;
    private List<Plan> plan;
    private volatile TaskDetail taskDetail;

    public TaskDetail getTaskDetail(){
        if (taskDetail==null){
        synchronized (Task.class){
            if (taskDetail==null){
                taskDetail=new TaskDetail();
            }
       }
        }
        return taskDetail;
    }

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
