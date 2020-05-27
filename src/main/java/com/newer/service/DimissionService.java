package com.newer.service;

import com.github.pagehelper.PageInfo;
import com.newer.domain.Dimission;
import com.newer.domain.User;
import com.newer.dto.PageDto;
import com.newer.util.CommonsResult;

import java.util.List;

/**
 * Create by 何辉
 * 2020/5/21 14:07
 */
public interface DimissionService {
    public CommonsResult addDimission(Dimission dimission);
    public PageInfo showDimission(User user,PageDto pageDto);
    public  CommonsResult reject(User user,Dimission dimission);
    public  CommonsResult ratify(User user,Dimission dimission);
    public CommonsResult updateDimission(Dimission dimission);
}
