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
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select seq_t_overtim_overtimID .nextval from dual")
    private Integer overtimID;
    private Integer userid;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Data overtimedate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Data  stoPovertime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Data  overtimedated;
    private  String overtimstate;
    private  String overtimReason ;
    private Integer  approver;
    private String remark;
}
