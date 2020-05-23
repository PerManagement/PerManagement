package com.newer.controller;

import com.github.pagehelper.PageInfo;
import com.newer.domain.Dimission;
import com.newer.domain.Resource;
import com.newer.domain.User;
import com.newer.dto.PageDto;
import com.newer.service.DimissionService;
import com.newer.service.ResourceService;
import com.newer.util.CommonsResult;
import com.newer.util.Sessions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    @GetMapping("reject")
    public CommonsResult reject(Integer id,HttpSession session,ModelMap modelMap)throws ParseException {
        User user =(User)modelMap.getAttribute(Sessions.SESSION_LOGIN_USER);
        return null;
    }
}
