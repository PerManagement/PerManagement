package com.newer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name="t_leave")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select seq_t_leave_leaveID.nextval from dual")
    private Integer leaveid;
    private Integer userid;
    private String leavereason;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Data leavetime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Data stopleave;
    private String context;
    private  String approval;
    private  String leavestate;
    @Column(name="final")
    private  String finall;
    private Integer approver;
    private  String  remark;
    private BigDecimal saltotal;
    private Integer duration;

}
