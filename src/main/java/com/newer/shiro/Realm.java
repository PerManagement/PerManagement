package com.newer.shiro;

import com.newer.domain.*;
import com.newer.service.UserService;
import lombok.SneakyThrows;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Create by 何辉
 * 2020/3/28 11:07
 */
public class Realm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    ModelMap modelMap=new ModelMap();

    @SneakyThrows
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection){

        //1.从PrincipalCollection获取登录用户的信息
        User user= (User)principalCollection.getPrimaryPrincipal();
        //2.利用登录的用户信息来用来用户当前用户的角色或权限（可能需要查询数据库）
        Set roles = new HashSet<>();
        for(UserRole userRole:user.getUserRoles()){
            for (Role role:userRole.getRoles()){
                for (RoleResource roleResource:role.getRoleResources()){
                    for (Resource resource:roleResource.getResources()){
                        if (resource.getResourcecode()==null){
                            continue;
                        }
                        roles.add(resource.getResourcecode());
                    }
                }
            }
        }
        return new SimpleAuthorizationInfo(roles);
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)  throws AuthenticationException {
        //1.把AuthenticationToken转换成UsernamePasswordToken
        UsernamePasswordToken uptoken = (UsernamePasswordToken) authenticationToken;
        //2.从UsernamePasswordToken中来获取username
        String userName = uptoken.getUsername();
        //3.调用业务层方法，从数据库中查询对应的用户记录
        User user = this.userService.login(userName);
        if (user == null) {
            //4.若用户不存在则可以抛出UnknownAccountException异常
            throw new UnknownAccountException("用户不存在..");
        }
        if (user.getLocked()!=0){
            throw new LockedAccountException("用户被限制登录");
        }
        if (user.getDeletestatus()!=0){
            throw new UnknownAccountException("用户已离职");
        }
        //6.根据用户的情况，来构建AuthenticationInfo对象并返回，通常使用的实现类为：SimpAuthenticationInfo
        //以下信息是从数据库中获取的
        //1.principal：认证的实体信息 可以是username,也可以时数据表对应的实体类对象
        User principal=user;
        //2.creadentials:密码
//        Object creadenials=user.getSalt();
//        System.out.println("加密后密码："+creadenials);
        //3.realmName:当前realm对象的name，调用父类的getName()方法即可
        String realmName=getName();
        //4.盐值
        //ByteSource credentialsSalt = ByteSource.Util.bytes(userName);
        SimpleAuthenticationInfo info = null; //new SimpleAuthenticationInfo(principal,creadentials,realmName);
        info = new SimpleAuthenticationInfo(principal, user.getPassword(), realmName);
        return info;
    }
}
