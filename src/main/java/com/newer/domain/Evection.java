package com.newer.domain;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private Date evectiondate;
    private String evectionaddress;
    private String evectionreason ;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date stopevection;
    private String evectionstate  ;
    public Integer  approver;
    private String  remark ;
    private User user;

    public String getEvectiondateString(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(this.evectiondate!=null){
            return sdf.format(this.evectiondate);
        }
        return null;
    }
    public String getStopevectionString(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(this.stopevection!=null){
            return sdf.format(this.stopevection);
        }
        return null;
    }
}
