package com.newer.controller;

import com.github.pagehelper.PageInfo;
import com.newer.domain.Task;
import com.newer.domain.User;
import com.newer.dto.PageDto;
import com.newer.service.TaskService;
import com.newer.service.UserService;
import com.newer.util.CommonsResult;
import com.newer.util.Sessions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 功能描述：任务管理模块控制层
 * 作者：谢海鸿
 * 时间：2020-04-07 21:20
 */

@RestController
@RequestMapping("task")
@SessionAttributes(Sessions.SESSION_LOGIN_USER)
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    //查询直系下属 05-04 15:10
    @GetMapping("findExecutor")
    public CommonsResult findExecutor(ModelMap modelMap){
        User attribute = (User)modelMap.getAttribute(Sessions.SESSION_LOGIN_USER);
        List<User> list=this.userService.findExecutor(attribute.getUserid());
        return new CommonsResult(200,"查询直系下属",list);
    }
    //创建任务 2020-05-02 16:24
    @PostMapping("save")
    public CommonsResult save(@RequestBody Task task,ModelMap modelMap){
        User user = (User)modelMap.getAttribute(Sessions.SESSION_LOGIN_USER);
        task.setSharer(user.getUserid());
        boolean tag=this.taskService.save(task);
        if (tag){
            return new CommonsResult(200,"创建任务成功",task);
        }
        return new CommonsResult(200,"创建任务失败",null);
    }

    //任务查询 2020-05-02 17:40
    @GetMapping("findTask")
    public CommonsResult findTask(PageDto dto){
        PageInfo pageInfo=this.taskService.findTask(dto);
        return new CommonsResult(200,"任务分页",pageInfo);
    }

    //修改任务 2020-05-02 23:11
    @PostMapping("update")
    public CommonsResult update(@RequestBody Task task){
        boolean tag=this.taskService.update(task);
        if (tag){
            return new CommonsResult(200,"修改任务成功",task);
        }
        return new CommonsResult(200,"修改任务失败",null);
    }


}
