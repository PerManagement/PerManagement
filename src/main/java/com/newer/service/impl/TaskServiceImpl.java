package com.newer.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newer.dao.TaskDetailMapper;
import com.newer.dao.TaskMapper;
import com.newer.domain.Task;
import com.newer.domain.TaskDetail;
import com.newer.dto.TaskDto;
import com.newer.service.TaskService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
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
    @Autowired
    private TaskDetailMapper taskDetailMapper;

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
    public boolean updateCheck(Integer taskid) {
        Task task=new Task();
        task.setStatus("已审核");
        task.setReason("此任务通过审核");
        Example example=new Example(task.getClass());
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("taskid",taskid);
        return this.taskMapper.updateByExampleSelective(task,example)>0?true:false;
    }

    @Override
    public boolean updateReason(Task task) {
        task.setStatus("未通过");
        return this.taskMapper.updateByPrimaryKeySelective(task)>0?true:false;
    }

    @Override
    public PageInfo<Task> findTask(@Param("taskDto")TaskDto taskDto) {
        PageHelper.startPage(taskDto.getPage(), taskDto.getPageSize());
        List<Task> list=this.taskMapper.findTask(taskDto);
        PageInfo<Task> pageInfo=new PageInfo<Task>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<Task> findTaskByUserId(TaskDto taskDto) {
        PageHelper.startPage(taskDto.getPage(),taskDto.getPageSize());
        List<Task> list=this.taskMapper.findTaskByUserId(taskDto.getUserid());
        PageInfo<Task> pageInfo=new PageInfo<Task>(list);
        return pageInfo;
    }

    @Override
    public List<Task> findAssociateWith(Integer sharer) {
        return this.taskMapper.findAssociateWith(sharer);
    }

    @Override
    @Transactional
    public boolean handover(Task task) {

        int count=this.taskMapper.updateByPrimaryKeySelective(task);
        if(count>0){
            TaskDetail taskDetail = new TaskDetail();
            taskDetail.setTaskid(task.getTaskid());
            taskDetail.setSharer(task.getSharer());
            taskDetail.setHandover(task.getTaskDetail().getHandover());
            taskDetail.setExecutant(task.getTaskDetail().getExecutant());
            taskDetail.setRecorddate(new Date());
            taskDetail.setDetaildesc("归档");
            this.taskDetailMapper.insertSelective(taskDetail);
            return true;
        }else{
            return  false;
        }
    }

    @Override
    public List<Task> findTaskByUserid(Integer userId) {
        return this.taskMapper.findTaskByUserid(userId);
    }

    @Override
    public List<Task> checkTask(Integer id) {
        return this.taskMapper.checkTask(id);
    }

//    @Override
//    public Task findPlan(Integer taskid) {
//        return this.taskMapper.findPlan(taskid);
//    }

}
