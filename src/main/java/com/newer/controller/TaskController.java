package com.newer.controller;

import com.github.pagehelper.PageInfo;
import com.newer.domain.Dimission;
import com.newer.domain.Task;
import com.newer.domain.TaskDetail;
import com.newer.domain.User;
import com.newer.dto.TaskDetailDto;
import com.newer.dto.TaskDto;
import com.newer.service.DimissionService;
import com.newer.service.TaskService;
import com.newer.service.UserService;
import com.newer.util.CommonsResult;
import com.newer.util.Sessions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private DimissionService dimissionService;

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
        return new CommonsResult(500,"创建任务失败",null);
    }

    //任务查询 2020-05-02 17:40
    @GetMapping("findTask")
    public CommonsResult findTask(TaskDto taskDto){
        System.out.println("taskDto"+taskDto);
        PageInfo pageInfo=this.taskService.findTask(taskDto);
        return new CommonsResult(200,"任务分页",pageInfo);
    }

    //查询当前用户的任务 2020-05-02 17:40
    @GetMapping("findTaskByUserId")
    public CommonsResult findTaskByUserId(TaskDto taskDto){
        PageInfo pageInfo=this.taskService.findTaskByUserId(taskDto);
        return new CommonsResult(200,"查询当前用户的任务",pageInfo);
    }

    //修改任务 2020-05-02 23:11
    @PostMapping("update")
    public CommonsResult update(@RequestBody Task task){
        boolean tag=this.taskService.update(task);
        if (tag){
            return new CommonsResult(200,"修改任务成功",task);
        }
        return new CommonsResult(500,"修改任务失败",null);
    }

    @GetMapping("findAssociateWith")
    public CommonsResult findAssociateWith(Integer sharer) {
        List list=this.taskService.findAssociateWith(sharer);
        return new CommonsResult(200,"查询未交接任务",list);
    }


    @GetMapping("findUsers")
    public CommonsResult findUsers(int userid,ModelMap modelMap){
        User attribute = (User)modelMap.getAttribute(Sessions.SESSION_LOGIN_USER);
        List<User> list=this.userService.findUsers(attribute.getUserid(),userid);
        return new CommonsResult(200,"查询直系下属",list);
    }

    @PostMapping("handover")
    public CommonsResult handover(@RequestBody TaskDetailDto taskDetailDto){
        Task task=new Task();
        task.getTaskDetail().setExecutant(taskDetailDto.getExecutant());
        task.getTaskDetail().setHandover(taskDetailDto.getUserid());
        task.setTaskid(taskDetailDto.getTaskid());
        task.setSharer(taskDetailDto.getSharer());
        task.setTaskname(taskDetailDto.getTaskname());
        task.setStatus(taskDetailDto.getStatus());
        task.setBegindate(taskDetailDto.getBegindate());
        task.setEnddate(taskDetailDto.getEnddate());
        task.setTaskdesc(taskDetailDto.getTaskdesc());
        task.setUserid(taskDetailDto.getUserid());
        boolean tag=this.taskService.handover(task);
        if (tag){
            return new CommonsResult(200,"任务交接成功",taskDetailDto);
        }
        return new CommonsResult(500,"任务交接失败",null);
    }

    @GetMapping("findTaskByUserid")
    public CommonsResult findTaskByUserid(int userid){
        List<Task> list=this.taskService.findTaskByUserid(userid);
        return new CommonsResult(200,"查询这个员工的相应任务",list);
    }

    @GetMapping("checkTask")
    public CommonsResult checkTask(Integer userid) {
        List list=this.taskService.checkTask(userid);
        return new CommonsResult(200,"查询未审核任务",list);
    }

    @GetMapping("updateCheck")
    public CommonsResult updateCheck(Integer taskid){
        boolean tag=this.taskService.updateCheck(taskid);
        if (tag){
            return new CommonsResult(200,"审核任务成功",tag);
        }
        return new CommonsResult(500,"审核任务失败",null);
    }


    @PostMapping("updateReason")
    public CommonsResult updateReason(@RequestBody Task task){
        boolean tag=this.taskService.updateReason(task);
        if (tag){
            return new CommonsResult(200,"此任务未通过审核",task);
        }
        return new CommonsResult(500,"此任务修改审核失败",null);
    }

    @GetMapping("dimHandover")
    public CommonsResult dimHandover(Integer userid) {
        List list=this.taskService.dimHandover(userid);
        return new CommonsResult(200,"查询要离职员工未完成的任务",list);
    }

    @PostMapping("handover1")
    public CommonsResult handover1(@RequestBody TaskDetailDto taskDetailDto){
        Task task=new Task();
        task.getTaskDetail().setExecutant(taskDetailDto.getExecutant());
        task.getTaskDetail().setHandover(taskDetailDto.getUserid());
        task.setTaskid(taskDetailDto.getTaskid());
        task.setSharer(taskDetailDto.getSharer());
        task.setTaskname(taskDetailDto.getTaskname());
        task.setStatus(taskDetailDto.getStatus());
        task.setBegindate(taskDetailDto.getBegindate());
        task.setEnddate(taskDetailDto.getEnddate());
        task.setTaskdesc(taskDetailDto.getTaskdesc());
        task.setUserid(taskDetailDto.getUserid());
        boolean tag=this.taskService.handover(task);
        if (tag){
            List<Task> list=this.taskService.dimHandover(taskDetailDto.getExecutant());
            if(list.size()<=0){
                Dimission dimission=new Dimission();
                dimission.setDmissionId(taskDetailDto.getDmissionId());
                dimission.setExistTask("否");
                dimission.setHeir(taskDetailDto.getUserid());
                CommonsResult commonsResult = this.dimissionService.updateDimission(dimission);
                return commonsResult;
            }
            return new CommonsResult(200,"任务交接成功",taskDetailDto);
        }
        return new CommonsResult(500,"任务交接失败",null);
    }
}
