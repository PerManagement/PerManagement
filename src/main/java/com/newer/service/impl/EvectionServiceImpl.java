package com.newer.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newer.dao.EvectionDaoMapper;
import com.newer.domain.Evection;
import com.newer.dto.PageDto;
import com.newer.service.EvectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * 加班实现
 */
@Service
public class EvectionServiceImpl implements EvectionService{
    @Autowired
    private EvectionDaoMapper evectionDaoMapper;
    @Override
    public PageInfo findlhl(PageDto dto) {
            PageHelper.startPage(dto.getPage(), dto.getPageSize());
            List list = this.evectionDaoMapper.findAal();
            PageInfo pageInfo = new PageInfo<>(list);
            return pageInfo;
        }

    @Override
    public PageInfo<Evection> findAal2(PageDto dto) {
        PageHelper.startPage(dto.getPage(), dto.getPageSize());
        List list = this.evectionDaoMapper.findAal2();
        PageInfo pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int save(Evection evection) {
        int a=this.evectionDaoMapper.insertSelective(evection);
        if(a>0){
            return  a;
        }
        return 0;
    }

    @Override
    public int updateState(Integer evectionid) {
        int a=this.evectionDaoMapper.updateState2(evectionid);
        if(a>0){
            return a;
        }
        return 0;
    }


    @Override
    public int updateState2(Integer evectionid) {
        int a=this.evectionDaoMapper.updateState2(evectionid);
        if(a>0){
            return a;
        }
        return 0;
    }

    @Override
    public int updatelhl(Integer userid, Integer evectionid) {
        Evection evection=new Evection();
        evection.setUserid(userid);
        evection.setEvectionid(evectionid);
        int a=this.evectionDaoMapper.updatelhl(userid,evectionid);
        if(a>0){
            return a;
        }
        return 0;

    }

    @Override
    public int updateState3(Integer evectionid, Integer approver, String remark) {
        Evection evection=new Evection();
        evection.setApprover(approver);
        evection.setEvectionstate("驳回");
        evection.setRemark(remark);
        evection.setEvectionid(evectionid);
        Example exmaple=new Example(Evection.class);
        Example.Criteria criteria=exmaple.createCriteria();
        criteria.andEqualTo("evectionid",evectionid);
        int a=this.evectionDaoMapper.updateByExampleSelective(evection,exmaple);
        if(a>0){
            return a;
        }
        return 0;
    }

    @Override
    public int updatelhl2(Integer evectionid, Date evectiondate,String evectionaddress, String evectionreason, Date stopevection) {
        Evection evection=new Evection();
        evection.setEvectiondate(evectiondate);
        evection.setEvectionaddress(evectionaddress);
        evection.setEvectionreason(evectionreason);
        evection.setStopevection(stopevection);
        Example exmaple=new Example(Evection.class);
        Example.Criteria criteria=exmaple.createCriteria();
        criteria.andEqualTo("leaveid",evectionid);
        int a=this.evectionDaoMapper.updateByExampleSelective(evection,exmaple);
        if(a>0){
            return a;
        }
        return 0;
    }
}

