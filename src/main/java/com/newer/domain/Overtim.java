package com.newer.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Table(name ="t_overtim")
@Getter
@Setter
public class Overtim {
    @Id
    @Column(name="overtimID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select seq_t_overtim_overtimID .nextval from dual")
    private Integer overtimID;
    @Column(name="USERID")
    private Integer userid;
    @Column(name="overtimeDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Data overtimedate;
    @Column(name="stopovertime ")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Data  stoPovertime;
    @Column(name="overtimeDated")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Data  overtimedated;
    @Column(name="overtimState")
    private  String overtimstate;
    @Column(name="overtimReason ")
    private  String overtimReason ;
    private Integer  approver;
    private String remark;
}
