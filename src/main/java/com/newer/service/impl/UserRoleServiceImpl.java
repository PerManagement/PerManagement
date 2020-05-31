package com.newer.service.impl;

import com.newer.dao.RoleResourceDaoMapper;
import com.newer.dao.UserRoleDaoMapper;
import com.newer.domain.RoleResource;
import com.newer.domain.UserRole;
import com.newer.service.UserRoleService;
import com.newer.util.CommonsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Create by 何辉
 * 2020/3/29 16:20
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleDaoMapper userRoleDao;
    @Autowired
    private RoleResourceDaoMapper roleResource;
    @Override
    public List<UserRole> getUserRolebyUserId(Integer id) {
        return this.userRoleDao.getUserRolebyUserId(id);
    }
    @Override
    @Transactional
    public CommonsResult insert(Integer userid, Integer id) {

        Example example=new Example(RoleResource.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("resourceid",id);
        criteria.andNotEqualTo("roleid",7);
        List<RoleResource> roleResources = this.roleResource.selectByExample(example);
        for (RoleResource roleResource:roleResources){

            Integer roleid = roleResource.getRoleid();
            Example example1=new Example(UserRole.class);
            Example.Criteria criteria1=example1.createCriteria();
            criteria1.andEqualTo("userid",userid);
            criteria1.andEqualTo("roleid",roleid);
            List<UserRole> userRoles = this.userRoleDao.selectByExample(example1);
            if(userRoles.size()<=0){
                UserRole userRole=new UserRole();
                userRole.setUserid(userid);
                userRole.setRoleid(roleid);
                this.userRoleDao.insertSelective(userRole);

        }

        }
        return new CommonsResult(200,"操作成功",null);
    }

    @Override
    public int deleteId(Integer userid) {
        Example a=new Example(UserRole.class);
        Example.Criteria b=a.createCriteria();
        b.andEqualTo("userid",userid);
        int i = this.userRoleDao.deleteByExample(a);
        return i;
    }
}
