package com.newer.controller;

import com.alibaba.druid.sql.visitor.functions.Substring;
import com.newer.util.CommonsResult;
import com.newer.util.Sessions;
import com.newer.domain.User;
import com.newer.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Create by 何辉
 * 2020/3/28 11:22
 */
@RestController
@SessionAttributes(Sessions.SESSION_LOGIN_USER)
@RequestMapping("user")
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
        return new CommonsResult(200,"登录成功",user1);
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
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
