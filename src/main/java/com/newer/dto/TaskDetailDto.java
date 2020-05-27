package com.newer.dto;

import com.newer.domain.Plan;
import com.newer.domain.Task;
import com.newer.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

/**
 * @Author 谢海鸿
 * @Date 2020-05-18 14:51
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskDetailDto {
    public Integer taskid;
    public String taskname;
    public Date begindate;
    public Date enddate;
    public String status;
    public Integer userid;
    public Integer sharer;
    public String taskdesc;
    private Integer executant;
    @Column(name = "DMISSION_ID")
    private Integer dmissionId;
}
