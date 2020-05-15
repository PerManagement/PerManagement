package com.newer.controller;

import com.newer.domain.Resource;
import com.newer.domain.User;
import com.newer.service.ResourceService;
import com.newer.service.UserService;
import com.newer.util.CommonsResult;
import com.newer.util.Sessions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

/**
 * Create by 何辉
 * 2020/5/2 20:09
 */
@RestController
@RequestMapping("user")
@SessionAttributes(Sessions.SESSION_LOGIN_USER)
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    ResourceService resourceService;
    @GetMapping("createUser")
    public CommonsResult createUser(){
        List<Resource> allURL = resourceService.findAll();

        return new CommonsResult(200,"createUser","");
    }
    @GetMapping("/hello")
    public User hello(ModelMap modelMap){
        User attribute = (User)modelMap.getAttribute(Sessions.SESSION_LOGIN_USER);
        System.out.println(attribute);
        return (User)modelMap.getAttribute(Sessions.SESSION_LOGIN_USER);
    }
}
