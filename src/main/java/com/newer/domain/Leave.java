package com.newer.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Table(name="t_leave")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select seq_t_leave_leaveID.nextval from dual")
    private Integer leaveid;
    private Integer userid;
    private String leavereason;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String leavetime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String stopleave;
    private String context;
    private  String approval;
    private  String leavestate;
    /**
     * 因为它是关键字所以在末尾多加了一个l
     */
    @Column(name="final")
    private  String finall;
    private Integer approver;
    private  String  remark;
    private User user;
}
