package com.newer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author 谢海鸿
 * @Date 2020-05-17 20:33
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="taskdetail")
public class TaskDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = " select seq_taskdetail_id.nextval from dual ")
    private Integer taskdetailid;
    private Integer taskid;
    private Integer sharer;
    private Integer handover;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date recorddate;
    private String detaildesc;
    private Integer executant;
}
