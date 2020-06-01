package com.newer.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newer.dao.AfficheMapper;
import com.newer.domain.Affiche;
import com.newer.dto.AfficheDto;
import com.newer.service.AfficheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 公告模块业务层实现类
 * 2020-05-02
 * 陈良吉
 */
@Service
public class AfficheServiceImpl implements AfficheService {

    @Autowired
    private AfficheMapper afficheMapper;

    @Override
    public PageInfo<Affiche> findAffiches(AfficheDto afficheDto) {

        PageHelper.startPage(afficheDto.getPage(), afficheDto.getPageSize());

        Example example=new Example(Affiche.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("userid",afficheDto.getUserid());

//        List list =this.afficheMapper.selectByExample(example);
        List list = this.afficheMapper.findAffiches(afficheDto.getUserid());
        PageInfo<Affiche> pageInfo = new PageInfo<Affiche>(list);
        return pageInfo;
    }

    @Override
    public boolean saveAffiche(Affiche affiche) {
        return this.afficheMapper.insert(affiche) > 0 ? true : false;
    }
    @Override
    public Affiche findAfficheByDate() {
        return this.afficheMapper.findAfficheByDate();
    }
}

