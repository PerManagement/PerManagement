package com.newer.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.SimpleDateFormat;

/**
 * 考勤表 周怡珊
 */
@Table(name ="t_attendance")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select seq_t_attendance_attendanceID.nextval from dual")
    private Integer attendanceid;
    private Integer userid;
    private Integer deptid;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private  String morninghours;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private String afternoonclosingtime ;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private String recorddate;
    private  String remark;
    private User user;
    private  Department department;

//    public String getAfternoonclosingtimeString(){
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        if(this.afternoonclosingtime!=null){
//            return sdf.format(this.afternoonclosingtime);
//        }
//        return null;
//    }
//
//    public String getMorninghoursString(){
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        if(this.morninghours!=null){
//            return sdf.format(this.morninghours);
//        }
//        return null;
//    }
//    public String getRecorddateString(){
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        if(this.recorddate!=null){
//            return sdf.format(this.recorddate);
//        }
//        return null;
//    }

}
