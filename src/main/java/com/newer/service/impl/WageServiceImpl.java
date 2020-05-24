package com.newer.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newer.dao.WageMapper;
import com.newer.domain.Wage;
import com.newer.dto.WagePageDto;
import com.newer.service.WageService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

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
    public int updateWage(Integer userId,Integer wageId) {
        System.out.println(userId+","+wageId);
        Wage wage = new Wage();
        wage.setIssuer(userId);
        wage.setIssuestate("已发放");
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
    public int updateState2(Integer wageId) {
        int a=this.wageMapper.updateState2(wageId);
        if(a>0){
            return a;
        }
        return 0;
    }

    @Override
    public int updateState3(Integer wageId) {
        int a=this.wageMapper.updateState3(wageId);
        if(a>0){
            return a;
        }
        return 0;
    }

    @Override
    public PageInfo<Wage> pageInfo(WagePageDto wageDto) {
        PageHelper.startPage(wageDto.getPage(),wageDto.getPageSize());
        List list=this.wageMapper.pageInfo();
        PageInfo pageInfo=new PageInfo<Wage>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<Wage> pageInfo2(WagePageDto wageDto) {
        PageHelper.startPage(wageDto.getPage(),wageDto.getPageSize());
        List list=this.wageMapper.pageInfo2();
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
        int a=this.wageMapper.insert(wage);
        if(a>0){
            return  a;
        }
        return 0;
    }
}
