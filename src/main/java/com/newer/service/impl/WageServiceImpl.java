package com.newer.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newer.dao.*;
import com.newer.domain.Wage;
import com.newer.domain.Welfare;
import com.newer.dto.WagePageDto;
import com.newer.service.WageService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * Create by 龙珊
 * 2020/4/27 21:15
 */
@Service
public class WageServiceImpl implements WageService {
    @Autowired
    public WageMapper wageMapper;

    @Override
    public int update(Wage wage) {
        int a=this.wageMapper.updateByPrimaryKeySelective(wage);

        return 0;
    }

    @Override
    public int updateWage(Integer userId,Integer wageId) {
        System.out.println(userId+","+wageId);
        Wage wage = new Wage();
        wage.setIssuer(userId);
        wage.setIssuestate("已发放");
        wage.setWagedate(new Date());
        System.out.println(wage);
        Example example=new Example(wage.getClass());
        Criteria criteria=example.createCriteria();
        criteria.andEqualTo("wageid",wageId);
        int a=this.wageMapper.updateByExampleSelective(wage,example);
        if(a>0){
            return a;
        }
        return 0;
    }

    @Override
    public int updateState(Integer wageId) {
        int a=this.wageMapper.updateState(wageId);
        if(a>0){
            return a;
        }
        return 0;
    }

    @Override
    public int updateState2(Integer wageId,String remark) {
        Wage wage = new Wage();
        wage.setWageid(wageId);
        wage.setRemark(remark);
        wage.setWagestate("驳回");
        Example example=new Example(wage.getClass());
        Criteria criteria=example.createCriteria();
        criteria.andEqualTo("wageid",wageId);
        int a=this.wageMapper.updateByExampleSelective(wage,example);
        if(a>0){
            return a;
        }
        return 0;
    }

    @Override
    public int updateState3(Integer wageId,BigDecimal netPayroll) {
        Wage wage = new Wage();
        wage.setWageid(wageId);
        wage.setNetpayroll(netPayroll);
        wage.setWagestate("未审核");
        Example example=new Example(wage.getClass());
        Criteria criteria=example.createCriteria();
        criteria.andEqualTo("wageid",wageId);
        int a=this.wageMapper.updateByExampleSelective(wage,example);
        if(a>0){
            return a;
        }
        return 0;
    }

    @Override
    public PageInfo<Wage> pageInfo(WagePageDto wageDto) {
        PageHelper.startPage(wageDto.getPage(),wageDto.getPageSize());
        List<Wage> list=this.wageMapper.pageInfo();
        for(Wage w : list){
            if("事假".equals(w.getLeave().getLeavereason())){
                BigDecimal bigDecimal=new BigDecimal(21.75);
                BigDecimal bigDecima2=new BigDecimal(w.getLeave().getDuration());
                w.getLeave().setSaltotal(bigDecima2.multiply(w.getUser().getBasepay().divide(bigDecimal,2,BigDecimal.ROUND_HALF_UP)).setScale(2, BigDecimal.ROUND_HALF_UP));
            }
            if("病假".equals(w.getLeave().getLeavereason())){
                BigDecimal bigDecimal=new BigDecimal(21.75);
                BigDecimal bigDecima2=new BigDecimal(0.40);
                BigDecimal bigDecima3=new BigDecimal(w.getLeave().getDuration());
                w.getLeave().setSaltotal(bigDecima3.multiply(w.getUser().getBasepay().divide(bigDecimal,2, BigDecimal.ROUND_HALF_UP).multiply(bigDecima2).setScale(2, BigDecimal.ROUND_HALF_UP)).setScale(2, BigDecimal.ROUND_HALF_UP));
            }
            if (w.getOvertim().getCountovertim()==null){
                w.getOvertim().setCountovertim(0);
            }
            BigDecimal bigDecimal=new BigDecimal(0.01);
            BigDecimal bigDecimal2=new BigDecimal(w.getOvertim().getCountovertim());
            w.getOvertim().setCountsal(bigDecimal2.multiply(w.getUser().getBasepay().multiply(bigDecimal)));
        }
        PageInfo pageInfo=new PageInfo<Wage>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<Wage> pageInfo2(WagePageDto wageDto) {
        PageHelper.startPage(wageDto.getPage(),wageDto.getPageSize());
        List<Wage> list=this.wageMapper.pageInfo2();

        for(Wage w : list){
            if("事假".equals(w.getLeave().getLeavereason())){
                BigDecimal bigDecimal=new BigDecimal(21.75);
                BigDecimal bigDecima2=new BigDecimal(w.getLeave().getDuration());
                w.getLeave().setSaltotal(bigDecima2.multiply(w.getUser().getBasepay().divide(bigDecimal,2,BigDecimal.ROUND_HALF_UP)).setScale(2, BigDecimal.ROUND_HALF_UP));
            }
            if("病假".equals(w.getLeave().getLeavereason())){
                BigDecimal bigDecimal=new BigDecimal(21.75);
                BigDecimal bigDecima2=new BigDecimal(0.40);
                BigDecimal bigDecima3=new BigDecimal(w.getLeave().getDuration());
                w.getLeave().setSaltotal(bigDecima3.multiply(w.getUser().getBasepay().divide(bigDecimal,2, BigDecimal.ROUND_HALF_UP).multiply(bigDecima2).setScale(2, BigDecimal.ROUND_HALF_UP)).setScale(2, BigDecimal.ROUND_HALF_UP));
            }
            if (w.getOvertim().getCountovertim()==null){
                w.getOvertim().setCountovertim(0);
            }
            BigDecimal bigDecimal=new BigDecimal(0.1);
            BigDecimal bigDecimal2=new BigDecimal(w.getOvertim().getCountovertim());
            w.getOvertim().setCountsal(bigDecimal2.multiply(w.getUser().getBasepay().multiply(bigDecimal)));
        }
        PageInfo pageInfo=new PageInfo<Wage>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<Wage> pageInfoByDate(WagePageDto wageDto) {
        PageHelper.startPage(wageDto.getPage(),wageDto.getPageSize());
        List list=this.wageMapper.pageInfoByDate(wageDto);
        PageInfo pageInfo=new PageInfo<Wage>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<Wage> findByUserId(WagePageDto wageDto) {
        PageHelper.startPage(wageDto.getPage(),wageDto.getPageSize());
        List list=this.wageMapper.findByUserId(wageDto);
        PageInfo pageInfo=new PageInfo<Wage>(list);
        return pageInfo;
    }

    @Override
    public int save(Wage wage) {
        wage.setWagestate("未审批");
        wage.setIssuestate("未发放");
        wage.setIssuer(null);
        wage.setWagedate(null);
        int a=this.wageMapper.insert(wage);
        if(a>0){
            return  a;
        }
        return 0;
    }
}
