package com.newer.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 功能描述：任务管理试题类
 * 作者：谢海鸿
 * 时间：2020-04-28 21:25
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="t_pro_plan")
public class Plan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select seq_plan_planid.nextval from dual")
	private Integer planid;
	private Integer taskid;
	private String planname;
	private String state;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date begindate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date enddate;
	private String info;
	private String plandesc;
	private Task task;

	 
	 public String getEnddateString(){
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 if(this.enddate!=null){
			 return sdf.format(this.enddate);
		 }
		 return null;
	 }
	
	
}
