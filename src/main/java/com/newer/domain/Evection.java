package com.newer.domain;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

/**
 * 出差表周怡珊
 */
@Table(name ="t_evection")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select seq_t_evection_evectionID.nextval from dual")
    public Integer  evectionid;
    public Integer userid;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String evectiondate;
    private String evectionaddress;
    private String evectionreason ;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String stoPevection;
    private String evectionstate  ;
    public Integer  approver;
    private String  remark ;

}
