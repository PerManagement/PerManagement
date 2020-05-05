package com.newer.dao;

import com.newer.domain.User;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Create by 何辉
 * 2020/3/28 21:13
 */
public interface UserDaoMapper extends Mapper<User> {
    @Select("select * from t_tree_user where username=#{username}")
    @Results(
            @Result(column = "id",property = "userRoles",many=@Many(select="com.newer.dao.UserRoleDaoMapper.getUserRolebyUserId",fetchType= FetchType.EAGER))
    )
    public User login(String username);


    //谢海鸿  05-04 14:58 查询相关主管的下属
    @Select("select a.*,c.id cid,c.rolename from t_tree_user a,t_tree_user_role b,t_tree_role c where a.userid=b.userid and b.roleid=c.id and a.description='员工' and upno=#{id}")
    public List<User> findExecutor(Integer id);

    @Select("select * from t_tree_user where userid=#{userid}")
    @Results(
            @Result(column = "id",property = "userRoles",many=@Many(select="com.newer.dao.UserRoleDaoMapper.getUserRolebyUserId",fetchType= FetchType.EAGER))
    )
    public User findUserById(Integer userid);
}
