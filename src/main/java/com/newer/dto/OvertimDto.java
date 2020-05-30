package com.newer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="t_overtim")
public class OvertimDto extends PageDto{
    private Integer upno;
    private Integer userid;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select seq_t_overtim_overtimID .nextval from dual")
    private Integer overtimID;
//    private Data overtimedate;
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private Data  stoPovertime;
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private Data  overtimedated;
    private  String overtimstate;
    private  String overtimReason ;
    private Integer  approver;
    private String remark;
    private Integer countovertim;
    private BigDecimal countsal;
}
