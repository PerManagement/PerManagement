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
    public int update(Wage wage) {
        int a=this.wageMapper.updateByPrimaryKey(wage);
        if(a>0){
            return a;
        }
        return 0;
    }


    @Override
    public PageInfo<Wage> pageInfo(WagePageDto wageDto) {
        System.out.println(wageDto.getPage()+","+wageDto.getPageSize());
        System.out.println(wageDto.getBeginDate()+","+wageDto.getEndDate());
        PageHelper.startPage(wageDto.getPage(),wageDto.getPageSize());
        List list=this.wageMapper.pageInfoByDate(wageDto);
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
