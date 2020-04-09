package com.newer.service.impl;

import com.newer.dao.TaskMapper;
import com.newer.domain.Task;
import com.newer.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能描述：任务管理模块业务实现方法
 * 作者：谢海鸿
 * 时间：2020-04-07 21:15
 */
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskMapper taskMapper;

    @Override
    public boolean save(Task task) {
        return this.taskMapper.insertSelective(task)>0?true:false;
    }
}
