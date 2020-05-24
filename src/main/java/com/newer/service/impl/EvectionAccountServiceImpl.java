package com.newer.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newer.dao.EvectionAccountDaoMapper;
import com.newer.dao.EvectionDaoMapper;
import com.newer.domain.EvectionAccount;
import com.newer.dto.PageDto;
import com.newer.service.EvectionAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvectionAccountServiceImpl implements EvectionAccountService {
    @Autowired
    private EvectionAccountDaoMapper evectionAccountDaoMapper;
    @Override
    public PageInfo<EvectionAccount> findAal(PageDto dto) {
        PageHelper.startPage(dto.getPage(), dto.getPageSize());
        List list = this.evectionAccountDaoMapper.findAal();
        PageInfo pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int save(EvectionAccount evectionaccount) {
        int a=this.evectionAccountDaoMapper.insertSelective(evectionaccount);
        if(a>0){
            return  a;
        }
        return 0;
    }
}
