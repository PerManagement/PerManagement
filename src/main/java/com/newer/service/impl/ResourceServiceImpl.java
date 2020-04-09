package com.newer.service.impl;

import com.newer.dao.ResourceDaoMapper;
import com.newer.domain.Resource;
import com.newer.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by 何辉
 * 2020/4/4 14:00
 */
@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceDaoMapper daoMapper;

    /**
     * 返回所有URL
     * @return
     */
    @Override
    public List<Resource> findAllURL() {
        return  this.daoMapper.selectAll();
    }
}
