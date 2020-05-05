package com.newer.service;


import com.github.pagehelper.PageInfo;
import com.newer.domain.Wage;
import com.newer.dto.WagePageDto;

import java.util.Date;

/**
 * 功能描述：薪资管理模块业务层
 * 作者：龙珊
 * 时间：2020-04-27 21:10
 */
public interface WageService {
    public int save(Wage wage);
    public PageInfo<Wage> pageInfo(WagePageDto page);
    public PageInfo<Wage> pageInfo2(WagePageDto page);
    public PageInfo<Wage> pageInfoByDate(WagePageDto page);
    public PageInfo<Wage> findByUserId(WagePageDto page);
    public int updateWage(Integer userId,Integer wageId);
    public int updateState(Integer wageId);
    public int updateState2(Integer wageId);
    public int updateState3(Integer wageId);


}
