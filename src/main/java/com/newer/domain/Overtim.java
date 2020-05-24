package com.newer.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Table(name = "t_overtim")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Overtim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select seq_t_overtim_overtimID .nextval from dual")
    private Integer overtimid;
    private Integer userid;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date overtimedate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date stopovertime;
    private String overtimstate;
    private String overtimreason;
    private Integer approver;
    private String remark;
    private String overtimtype;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date overtimedated;
    private User user;

    public String getOvertimedateString(){
        if(this.overtimedate!=null){
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.overtimedate);
        }
        return null;
    }
    public String getStopovertimeString(){
        if(this.stopovertime!=null){
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.stopovertime);
        }
        return null;
    }
    public String getOvertimedatedString(){
        if(this.overtimedated!=null){
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.overtimedated);
        }
        return null;
    }
}
