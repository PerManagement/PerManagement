package com.newer.controller;

import com.alibaba.druid.sql.visitor.functions.Substring;
import com.newer.domain.*;
import com.newer.util.CommonsResult;
import com.newer.util.Sessions;
import com.newer.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Create by 何辉
 * 2020/3/28 11:22
 */
@RestController
@SessionAttributes(Sessions.SESSION_LOGIN_USER)
@RequestMapping("login")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("doLogin")
    public CommonsResult doLogin(@RequestBody User user,HttpSession session,ModelMap modelMap){
        System.out.println("接收到的登录信息:"+user);
        Subject subject= SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(user.getUsername(),user.getPassword()));
            System.out.println("登录成功！");
        }catch (AuthenticationException e){
            e.printStackTrace();
            System.out.println("登录失败！");
            String str=e.toString();
            System.out.println(str.substring(str.lastIndexOf(":")+2));
            return new CommonsResult(500,e.toString().substring(e.toString().lastIndexOf(":")+2),null);
        }
        User user1=(User)SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        modelMap.put(Sessions.SESSION_LOGIN_USER,user1);
        HashMap<String,String> map=new HashMap();
        for(UserRole userRole:user1.getUserRoles()){
            for (Role role:userRole.getRoles()){
                for (RoleResource roleResource:role.getRoleResources()){
                    for (Resource resource:roleResource.getResources()){
                        map.put(resource.getName(),resource.getName());
                    }
                }
            }
        }
        ArrayList list=new ArrayList();
        list.add(user1);
        list.add(map);
        return new CommonsResult(200,"登录成功",list);
    }

    @GetMapping("/hello")
    public User hello(ModelMap modelMap){
        System.out.println((User)modelMap.getAttribute(Sessions.SESSION_LOGIN_USER));
        return (User)modelMap.getAttribute(Sessions.SESSION_LOGIN_USER);
    }
    @GetMapping("/hello1")
    public String hello1(){
        return "hello1";
    }

    @GetMapping("/login")
    public CommonsResult login(){
        return new CommonsResult(301,"未登录","login");
    }
}
