package com.newer.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newer.dao.DimissionDaoMapper;
import com.newer.dao.TaskMapper;
import com.newer.dao.UserDaoMapper;
import com.newer.domain.Attendance;
import com.newer.domain.Dimission;
import com.newer.domain.Task;
import com.newer.domain.User;
import com.newer.dto.PageDto;
import com.newer.service.DimissionService;
import com.newer.service.TaskService;
import com.newer.util.CommonsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Create by 何辉
 * 2020/5/21 14:07
 */
@Service
public class DimissionServiceImpl implements DimissionService{
    @Autowired
    private DimissionDaoMapper daoMapper;
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private UserDaoMapper userDaoMapper;

    @Override
    @Transactional
    public CommonsResult addDimission(Dimission dimission) {
        //查看是否存在任务
        Example example = new Example(Task.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("userid",dimission.getUserid());
        List<Task> list = this.taskMapper.selectByExample(example);
        if (list.size()>0){
            dimission.setExistTask("是");
        }else{
            dimission.setExistTask("否");
        }
        //如果已经在申请则不能重复申请
        Example example1 = new Example(Dimission.class);
        Example.Criteria criteria1=example.createCriteria();
        criteria.andEqualTo("userid",dimission.getUserid());
        List<Dimission> list1 = this.daoMapper.selectByExample(example);
        if (list1.size()!=0){
           return new CommonsResult(201,"您的离职申请正在审批中。。。",null);
        }
        int i = this.daoMapper.insertSelective(dimission);
        return i>0?new CommonsResult(201,"申请成功，请耐心等待。。。",null):new CommonsResult(201,"发生未知错误，请稍后再试。。。",null);
    }

    @Override
    public PageInfo showDimission(User user,PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getPageSize());
        List list = this.daoMapper.showDimission(user);
        PageInfo pageInfo = new PageInfo<Dimission>(list);
        return pageInfo;
    }

    //驳回离职请求
    @Override
    public CommonsResult reject(User user, Dimission dimission) {
        if("经理".equals(user.getDescription())){
            dimission.setState("驳回");
            dimission.setFinal1("驳回");
            dimission.setFinalId(user.getUserid());
            dimission.setStateId(user.getUserid());
            int i = this.daoMapper.updateByPrimaryKeySelective(dimission);
            return i>0?new CommonsResult(200,"操作成功",null):new CommonsResult(500,"发生未知错误，请稍后再试。。。",null);
        }
            dimission.setFinal1("驳回");
            dimission.setFinalId(user.getUserid());
            int i = this.daoMapper.updateByPrimaryKeySelective(dimission);
            return i>0?new CommonsResult(200,"操作成功",null):new CommonsResult(500,"发生未知错误，请稍后再试。。。",null);
    }

    @Override
    @Transactional
    public CommonsResult ratify(User user,Dimission dimission){
        try {
            if ("经理".equals(user.getDescription())){
                dimission.setState("批准");
                dimission.setStateId(user.getUserid());
                int i = this.daoMapper.updateByPrimaryKeySelective(dimission);
                return i>0?new CommonsResult(200,"操作成功",null):new CommonsResult(500,"操作失败",null);
            }
            dimission.setFinal1("批准");
            dimission.setFinalId(user.getUserid());
            int i = this.daoMapper.updateByPrimaryKeySelective(dimission);
            if (i>0){
                User user1 = this.userDaoMapper.selectByPrimaryKey(dimission.getUserid());
                user1.setDeletestatus(1);
                int i1 = this.userDaoMapper.updateByPrimaryKey(user1);
                //得到离职人员的下级员工，如果不为空则将其上级ID修改为null
                Example example=new Example(User.class);
                Example.Criteria criteria=example.createCriteria();
                criteria.andEqualTo("upno",user1.getUserid());
                List<User> users = this.userDaoMapper.selectByExample(example);
                if (users!=null){
                    for (User user2:users){
                        user2.setUpno(null);
                        int i2 = this.userDaoMapper.updateByPrimaryKey(user2);
                        System.out.println(i2);
                    }
                }
                return i1>0?new CommonsResult(200,"操作成功",null):new CommonsResult(500,"操作失败",null);
            }
            return new CommonsResult(500,"未知错误，请稍后再试。。。",null);
        } catch (Exception e) {
            return new CommonsResult(500,"操作失败",null);
        }

    }
}
