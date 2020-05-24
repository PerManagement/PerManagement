package com.newer.domain;

import com.newer.dto.LeaveDto;
import com.newer.dto.OvertimDto;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
    private String wagestate;
    private String issuestate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date wagedate;
    private Integer issuer;
    private String remark;
    private Integer appover;
    private User userapprover;
    private User userissuer;
    private User user;
    private Welfare welfare;
    private Department dept;
    private OvertimDto overtim;
    private Evection evection;
    private EvectionAccount evectionaccount;
    private Attendance attendance;
    private LeaveDto leave;


    public String getWagedateString(){
        if(this.wagedate!=null){
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.wagedate);
        }
        return null;
    }

}
