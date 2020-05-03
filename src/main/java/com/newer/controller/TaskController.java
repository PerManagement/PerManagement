package com.newer.controller;

import com.github.pagehelper.PageInfo;
import com.newer.domain.Task;
import com.newer.dto.PageDto;
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
    public CommonsResult save(@RequestBody Task task){
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
    @GetMapping("update")
    public CommonsResult update(@RequestBody Task task){
        boolean tag=this.taskService.update(task);
        if (tag){
            return new CommonsResult(200,"修改任务成功",task);
        }
        return new CommonsResult(200,"修改任务失败",null);
    }


}
