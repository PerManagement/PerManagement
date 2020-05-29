package com.newer.controller;

import com.newer.domain.Department;
import com.newer.domain.Resource;
import com.newer.domain.User;
import com.newer.service.DepartmentService;
import com.newer.service.ResourceService;
import com.newer.service.UserService;
import com.newer.util.CommonsResult;
import com.newer.util.Sessions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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

    @PostMapping("save")
    public CommonsResult save(@RequestBody User user){
        System.out.println(user);
        CommonsResult save = this.service.save(user);
        return save;
    }

    @GetMapping("findDeptMager")
    public CommonsResult findDeptMager(Integer deptid){
        List list=this.service.findDeptMager(deptid);
        return new CommonsResult(200,"查询部门经理成功",list);
    }
}
