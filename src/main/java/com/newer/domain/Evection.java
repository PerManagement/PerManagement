package com.newer.domain;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

/**
 * 出差表周怡珊
 */
@Table(name ="t_evection ")
@Getter
@Setter
public class Evection {
    @Id
    @Column(name="evectionID")
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select seq_t_evection_evectionID.nextval from dual")
    public Integer  evectionid;
    @Column(name="USERID")
    public Integer userid;
    @Column(name="evectionDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Data evectiondate;
    @Column(name="evectionAddress")
    private String evectionaddress;
    @Column(name=" evectionReason")
    private String evectionreason ;
    @Column(name="stoPevection")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Data stoPevection;
    @Column(name="evectionState")
    private String evectionstate  ;
    public Integer  approver;
    private String  remark ;

}
