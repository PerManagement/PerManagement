package com.newer.service;

import com.github.pagehelper.PageInfo;
import com.newer.domain.Task;
import com.newer.dto.PageDto;

/**
 * 功能描述：任务管理模块业务层
 * 作者：谢海鸿
 * 时间：2020-04-07 21:10
 */
public interface TaskService {
    public boolean save(Task task);
    public boolean update(Task task);
    public PageInfo<Task> findTask(PageDto dto);
}
