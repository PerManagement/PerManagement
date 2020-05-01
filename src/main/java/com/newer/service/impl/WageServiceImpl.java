package com.newer.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newer.dao.WageMapper;
import com.newer.domain.Wage;
import com.newer.dto.WagePageDto;
import com.newer.service.WageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

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
    public PageInfo<Wage> pageInfo(WagePageDto page) {
        System.out.println(page.getPage()+","+page.getPageSize());
        PageHelper.startPage(page.getPage(),page.getPageSize());
//        Example example = new Example(Wage.class);
//        Criteria criteria = example.createCriteria();
//        if (page.getBeginDate() != null && !"".equals(page.getBeginDate())) {
//            criteria.andLike("birthday1", "%" + page.getBeginDate() + "%");
//        }
//        if (page.getEndDate() != null && !"".equals(page.getEndDate())) {
//            criteria.andLike("birthday2", "%" + page.getEndDate() + "%");
//        }
        List list=this.wageMapper.pageInfo();
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
