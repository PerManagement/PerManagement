package com.newer.dao;

import com.newer.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * Create by 何辉
 * 2020/5/22 14:48
 */
public class DimissionProvider {
    public String showDimission(final @Param("user") User user){
        StringBuffer buffer=new StringBuffer();
                buffer.append(" select a.*,b.username from t_pro_dimission a,t_tree_user b where a.userid=b.userid ");
                if ("经理".equals(user.getDescription())){
                    buffer.append( " and b.upno = #{user.userid} and a.state is null " );
                }else{
                    buffer.append(" and a.state is not null and a.final is null and a.state = '批准' ");
                }
         return buffer.toString();
    }
}
