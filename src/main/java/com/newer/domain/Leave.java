package com.newer.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Table(name="t_leave")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select seq_t_leave_leaveID.nextval from dual")
    private Integer leaveid;
    private Integer userid;
    private String leavereason;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date leavetime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date stopleave;
    private String context;
    private  Integer approval;
    private  String leavestate;
    /**
     * 因为它是关键字所以在末尾多加了一个l
     */
    @Column(name="final")
    private  String finall;
    private Integer approver;
    private  String  remarks;
    private Integer duration;
    private  String  remark;
    private User user;
    public String getLeavetimeString(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(this.leavetime!=null){
            return sdf.format(this.leavetime);
        }
        return null;
    }
    public String getStopleaveString(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(this.stopleave!=null){
            return sdf.format(this.stopleave);
        }
        return null;
    }

}
