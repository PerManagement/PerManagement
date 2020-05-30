package com.newer.dao;

import com.newer.domain.User;
import com.newer.dto.UserDto;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import tk.mybatis.mapper.common.Mapper;

import javax.persistence.Column;
import java.util.List;

/**
 * Create by 何辉
 * 2020/3/28 21:13
 */
public interface UserDaoMapper extends Mapper<User> {
    @Select("select * from t_tree_user where username=#{username}")
    @Results(
            {
                    @Result(column = "userid",property = "userid"),
                    @Result(column = "userid",property = "userRoles",many=@Many(select="com.newer.dao.UserRoleDaoMapper.getUserRolebyUserId",fetchType= FetchType.EAGER))
            }
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

    @Select("select a.*,b.deptname from t_tree_user a,t_department b where a.deptid=b.deptid and a.deletestatus=0 ")
    @Results({
            @Result(column = "deptname",property = "department.deptname")
    })
    public List<User> showUser();
    @Select(" select a.*,b.realname upnoname from (select distinct a.*,nvl(b.taskname,'否') istask from (select a.*,b.deptname from t_tree_user a,t_department b where a.deptid=b.deptid and a.deletestatus=0)a left join t_pro_task b on a.userid=b.userid and b.status!='已完成') a left join t_tree_user b on a.upno=b.userid ")
    public List<UserDto> findUserTaskDept();
}
