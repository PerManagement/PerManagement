package com.newer.controller;

import com.github.pagehelper.PageInfo;
import com.newer.domain.*;
import com.newer.dto.PageDto;
import com.newer.dto.UpdatePowerDto;
import com.newer.dto.UserDto;
import com.newer.service.DimissionService;
import com.newer.service.ResourceService;
import com.newer.service.UserRoleService;
import com.newer.service.UserService;
import com.newer.util.CommonsResult;
import com.newer.util.Sessions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Create by 何辉
 * 2020/5/10 21:28
 */
@RestController
@RequestMapping("admin")
@SessionAttributes(Sessions.SESSION_LOGIN_USER)
public class AdminController {
@Autowired
    private ResourceService resourceService;
@Autowired
    private DimissionService dimissionService;
@Autowired
private UserService userService;
@Autowired
private UserRoleService userRoleService;

    //树形结构查询
    @GetMapping("tree")
    public CommonsResult tree(){
        List<Resource> tree = this.resourceService.tree(1);
        return new CommonsResult(200,"树形结构",tree);
    }

    //离职申请
    @PostMapping("addDimission")
    public CommonsResult addDimission(@RequestBody Dimission dimission,HttpSession session,ModelMap modelMap) throws ParseException {
        User user =(User)modelMap.getAttribute(Sessions.SESSION_LOGIN_USER);
        System.out.println(user);
        dimission.setUserid(user.getUserid());
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        dimission.setDimDate(simpleDateFormat.parse(format));
        dimission.setPosition(user.getDescription());
        CommonsResult commonsResult = this.dimissionService.addDimission(dimission);
        return commonsResult;
    }

    //查看离职申请
    @GetMapping("showDimission")
    public CommonsResult showDimission(HttpSession session,ModelMap modelMap)throws ParseException {
        User user =(User)modelMap.getAttribute(Sessions.SESSION_LOGIN_USER);
        PageInfo<Dimission> pageInfo = this.dimissionService.showDimission(user, new PageDto());
        return new CommonsResult(200,"",pageInfo);
    }

    //驳回离职请求
    @PostMapping("reject")
    public CommonsResult reject(@RequestBody Dimission dimission,HttpSession session,ModelMap modelMap)throws ParseException {
        User user =(User)modelMap.getAttribute(Sessions.SESSION_LOGIN_USER);
        CommonsResult reject = this.dimissionService.reject(user, dimission);
        return reject;
    }

    //批准离职请求
    @PostMapping("ratify")
    public CommonsResult ratify(@RequestBody Dimission dimission,HttpSession session,ModelMap modelMap)throws ParseException {
        User user =(User)modelMap.getAttribute(Sessions.SESSION_LOGIN_USER);
        CommonsResult reject = this.dimissionService.ratify(user,dimission);
        return reject;
    }

    //管理员操作，显示所有人员列表
    @GetMapping("showUser")
    public CommonsResult showUser(){
        List<User> users = this.userService.showUser();
        return new CommonsResult(200,"操作成功",users);
    }

    @PostMapping("updateLocked")
    public CommonsResult updateLocked(@RequestBody User user){
        CommonsResult<User> userCommonsResult = this.userService.updateLocked(user);
        return userCommonsResult;
    }


    @GetMapping("showRoleResource")
    public CommonsResult showRoleResource(Integer userid){
        List<UserRole> userRolebyUserId = this.userRoleService.getUserRolebyUserId(userid);
        HashSet<Integer> set=new HashSet<Integer>();
        for (UserRole userRole:userRolebyUserId){
            for(Role role:userRole.getRoles()){
                for (RoleResource roleResource:role.getRoleResources()){
                    for (Resource resource:roleResource.getResources()){
                        if(resource.getResourcecode()!=null){
                            set.add(resource.getId());
                        }
                    }
                }
            }
        }
        return new CommonsResult(200,"操作成功",set);
    }

    @PostMapping("updatePower")
    public CommonsResult updatePower(@RequestBody UpdatePowerDto updatePowerDto){
        System.out.println(updatePowerDto);

        for(int i=0;i<updatePowerDto.getIntegers().length;i++){
            this.userRoleService.insert(updatePowerDto.getUserid(),updatePowerDto.getIntegers()[i]);
        }
        return new CommonsResult(200,"操作成功",null);
    }

    @GetMapping("findUserTaskDept")
    public CommonsResult findUserTaskDept(){
        List<UserDto> userTaskDept = this.userService.findUserTaskDept();
        return new CommonsResult(200,"操作成功",userTaskDept);
    }

    @PostMapping("updateUser")
    public CommonsResult updateUser(@RequestBody User user){
        System.out.println(user);
        CommonsResult commonsResult = this.userService.updateUser(user);
        return commonsResult;
    }

    @GetMapping("showUpno")
    public CommonsResult showUpno(Integer upno){
        CommonsResult commonsResult = this.userService.showUpno(upno);
        return commonsResult;
    }
}
