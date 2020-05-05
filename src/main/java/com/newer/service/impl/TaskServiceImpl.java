package com.newer.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newer.dao.TaskMapper;
import com.newer.domain.Task;
import com.newer.dto.PageDto;
import com.newer.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        task.setStatus("未实施");
        return this.taskMapper.insertSelective(task)>0?true:false;
    }

    @Override
    public boolean update(Task task) {
        return this.taskMapper.updateByPrimaryKeySelective(task)>0?true:false;
    }

    @Override
    public PageInfo<Task> findTask(PageDto dto) {
        PageHelper.startPage(dto.getPage(), dto.getPageSize());
        List<Task> list=this.taskMapper.findTask();
        PageInfo<Task> pageInfo=new PageInfo<Task>(list);
        return pageInfo;
    }
}
