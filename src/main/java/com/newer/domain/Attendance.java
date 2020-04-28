package com.newer.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 考勤表 周怡珊
 */
@Table(name ="t_attendance")
@Getter
@Setter

public class Attendance {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select seq_t_attendance_attendanceID.nextval from dual")

    private Integer attendanceid;
    private Integer userid;
    private Integer deptid;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private  String morninghours;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String afternoonclosingtime ;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String recorddate;
    private  String remark;
}
