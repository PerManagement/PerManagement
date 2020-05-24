package com.newer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create by 何辉
 * 2020/5/21 14:01
 */
@Table(name = "t_pro_dimission")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Dimission {
    @Id
    @Column(name = "DMISSION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select t_pro_dimission_ID.Nextval from dual")
    private Integer dmissionId;
    private Integer userid;
    @Column(name = "DIM_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dimDate;
    private String position;
    @Column(name = "EXIST_TASK")
    private String existTask;
    private Integer heir;
    private String state;
    @Column(name = "state_id")
    private Integer stateId;
    @Column(name = "FINAL")
    private String final1;
    @Column(name = "final_id")
    private Integer finalId;
    private String feedback;
    @Column(name = "reject_reason")
    private String rejectReason;
    private User user;

    public String getDimDateString(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(this.dimDate!=null){
            return sdf.format(this.dimDate);
        }
        return null;
    }
}
