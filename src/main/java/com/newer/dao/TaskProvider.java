package com.newer.dao;

import com.newer.dto.TaskDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * @Author 谢海鸿
 * @Date 2020-05-06 19:42
 **/
public class TaskProvider {
    public String findTask(final @Param("taskDto") TaskDto taskDto){
        System.out.println("动态sQL:"+taskDto);
        return new SQL(){
            {
                SELECT(" a.*,b.username,b.realname,b.upno ");
                FROM(" t_pro_task a,t_tree_user b ");
                WHERE(" a.userid=b.userid ");
                if(taskDto!=null){
                    if(taskDto.getBegindate()!=null && !taskDto.getBegindate().equals("")){
                        taskDto.setBegindate(taskDto.getBegindate());
                        WHERE(" a.begindate >= to_date(#{taskDto.begindate},'yyyy-MM-dd') ");
                    }
                    if(taskDto.getEnddate()!=null && !taskDto.getEnddate().equals("")){
                        taskDto.setEnddate(taskDto.getEnddate());
                        WHERE(" a.enddate <= to_date(#{taskDto.enddate},'yyyy-MM-dd') ");
                    }
                }
            }
        }.toString();

    }
}

