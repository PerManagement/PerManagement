package com.newer.service;

import com.github.pagehelper.PageInfo;
import com.newer.domain.Task;
import com.newer.dto.TaskDto;

import java.util.List;

/**
 * 功能描述：任务管理模块业务层
 * 作者：谢海鸿
 * 时间：2020-04-07 21:10
 */
public interface TaskService {
    public boolean save(Task task);
    public boolean update(Task task);
    public boolean updateCheck(Integer taskid);
    public boolean updateReason(Task task);
    public PageInfo<Task> findTask(TaskDto dto);
    public PageInfo<Task>  findTaskByUserId(TaskDto taskDto);
    public List<Task> findAssociateWith(Integer sharer);

    //任务交接
    public boolean handover(Task task);
//    public Task findPlan(Integer taskid);

    public List<Task> findTaskByUserid(Integer userId);

    public List<Task> checkTask(Integer id);
}
