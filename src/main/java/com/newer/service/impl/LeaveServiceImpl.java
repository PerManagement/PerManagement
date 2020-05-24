package com.newer.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newer.dao.LeaveMapper;
import com.newer.domain.Leave;
import com.newer.dto.PageDto;
import com.newer.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 请假实现
 */
 @Service
public class LeaveServiceImpl implements LeaveService {
    @Autowired
    private LeaveMapper leaveMapper;
    private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public PageInfo<Leave> findlhl(PageDto dto) {
        System.out.println(dto.getPage() + "," + dto.getPageSize());
        PageHelper.startPage(dto.getPage(), dto.getPageSize());
        List list = this.leaveMapper.findlhl();
        PageInfo pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<Leave> findlhl2(PageDto dto) {
        PageHelper.startPage(dto.getPage(), dto.getPageSize());
        List list = this.leaveMapper.pageInfo();
        PageInfo pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<Leave> findlhl3(PageDto dto) {
        PageHelper.startPage(dto.getPage(), dto.getPageSize());
        List list = this.leaveMapper.pageInfo2();
        PageInfo pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int save(Leave leave) {

        int a=this.leaveMapper.insertSelective(leave);
        if(a>0){
            return  a;
        }
        return 0;
    }




    @Override
    public int updatelhl(Integer userid, Integer leaveid) {
        Leave leave=new Leave();
        leave.setUserid(userid);
        int a=this.leaveMapper.updatelhl(userid,leaveid);
        if(a>0){
            return a;
        }
        return 0;
    }

    @Override
    public int updatelhl2(Leave leave) {
        Example exmaple=new Example(Leave.class);
        Example.Criteria criteria=exmaple.createCriteria();
        criteria.andEqualTo("leaveid",leave.getLeaveid());
        leave.setLeavestate(null);
        leave.setFinall(null);
        leave.setApprover(null);
        leave.setApproval(null);
        int a=this.leaveMapper.updateByExampleSelective(leave,exmaple);
        if(a>0){
            return a;
        }
        return 0;
    }


    @Override
    public int updateState(Integer leaveid) {
        int a=this.leaveMapper.updateState(leaveid);
        if(a>0){
            return a;
        }
        return 0;
    }

    @Override
    public int updateState2(Integer leaveid) {
        int a=this.leaveMapper.updateState2(leaveid);
        if(a>0){
            return a;
        }
        return 0;
    }

    @Override
    public int updateState3(Integer leaveid,Integer approver,String remark) {
        Leave leave=new Leave();
        leave.setApprover(approver);
        leave.setFinall("驳回");
        leave.setRemark(remark);
        leave.setLeaveid(leaveid);
        Example exmaple=new Example(Leave.class);
        Example.Criteria criteria=exmaple.createCriteria();
        criteria.andEqualTo("leaveid",leaveid);
        int a=this.leaveMapper.updateByExampleSelective(leave,exmaple);
        if(a>0){
            return a;
        }
        return 0;
    }

    @Override
    public int updateState6(Integer leaveid, Integer approval, String remarks){
            Leave leave=new Leave();
            leave.setApprover(approval);
            leave.setFinall("驳回");
            leave.setRemark(remarks);
            leave.setLeaveid(leaveid);
            Example exmaple=new Example(Leave.class);
            Example.Criteria criteria=exmaple.createCriteria();
            criteria.andEqualTo("leaveid",leaveid);
            int a=this.leaveMapper.updateByExampleSelective(leave,exmaple);
            if(a>0){
                return a;
            }
            return 0;
    }

    @Override
    public int updateState5(Integer leaveid) {
        Leave leave=new Leave();
        leave.setLeavestate("");
        leave.setFinall("");
//        leave.setLeaveid(leaveid);
//        Example exmaple=new Example(leave.class);
        Example exmaple=new Example(leave.getClass());
        Example.Criteria criteria=exmaple.createCriteria();
        criteria.andEqualTo("leaveid",leaveid);
        int a=this.leaveMapper.updateByExampleSelective(leave,exmaple);
        if(a>0){
            return a;
        }
        return 0;
    }
}
