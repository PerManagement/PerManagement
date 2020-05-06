package com.newer.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Table(name ="t_overtim")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Overtim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select seq_t_overtim_overtimID.nextval from dual")
    private Integer overtimid;
    private Integer userid;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String overtimedate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String  stopovertime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String  overtimedated;
    private  String overtimstate;
    private  String overtimreason ;
    private Integer  approver;
    private String remark;
}
