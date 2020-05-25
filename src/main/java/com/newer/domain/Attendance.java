
        package com.newer.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date morninghours;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date afternoonclosingtime ;
    private String clockinstate ;
    private String clockoutstate ;
    private User user;



    public String getMorninghoursString(){
        if (this.morninghours != null)
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.morninghours);
        return null;
    }

    public String getAfternoonclosingtimeString(){
        if (this.afternoonclosingtime != null)
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.afternoonclosingtime);
        return null;
    }
}

