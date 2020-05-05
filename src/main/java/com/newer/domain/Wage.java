package com.newer.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="t_wage")
public class Wage {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY,generator="select seq_wage.nextval from dual")
    private Integer wageid;
    private Integer userid;
    private Integer deptid;
    private Integer welfareid;
    private BigDecimal netpay;
    private BigDecimal overtimepay;
    private BigDecimal taxes;
    private BigDecimal netpayroll;
    private BigDecimal absenteeism;
    private BigDecimal lateandearly;
    private BigDecimal leave;
    private String wagestate;
    private String issuestate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String wagedate;
    private Integer issuer;
    private User user;
    private Welfare welfare;

}