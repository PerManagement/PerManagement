package com.newer.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Table(name="t_leave")
@Getter
@Setter

public class Leave {
    @Id
    @Column(name="leaveID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select seq_t_leave_leaveID.nextval from dual")
    private Integer leaveid;
    @Column(name="USERID")
    private Integer userid;
    @Column(name="leaveReason")
    private String leavereason;
    @Column(name="leaveTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Data leavetime;
    @Column(name="stopLeave")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Data stopleave;
    private String context;
    private  String approval;
    @Column(name="leaveState")
    private  String leavestate;
    /**
     * 因为它是关键字所以在末尾多加了一个l
     */
    @Column(name="final")
    private  String finall ;
    private Integer approver;
    private  String  remark;
}
