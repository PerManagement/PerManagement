package com.newer.controller;

import com.newer.domain.Task;
import com.newer.service.TaskService;
import com.newer.util.CommonsResult;
import com.newer.util.Sessions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 功能描述：任务管理模块控制层
 * 作者：谢海鸿
 * 时间：2020-04-07 21:20
 */

@RestController
@RequestMapping("task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    //跳转创建任务界面
    @GetMapping("createTask")
    public CommonsResult goSave(){
        return new CommonsResult(200,"跳转创建任务界面","createTask");
    }

    //创建任务
    @GetMapping("save")
    public CommonsResult doSave(@RequestBody Task task){
        boolean tag=this.taskService.save(task);
        if (tag){
            return new CommonsResult(200,"创建任务成功",task);
        }
        return new CommonsResult(200,"创建任务失败",null);
    }
}
