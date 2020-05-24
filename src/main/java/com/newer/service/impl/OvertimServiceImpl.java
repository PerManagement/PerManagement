package com.newer.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newer.dao.OvertimMapper;
import com.newer.domain.Overtim;
import com.newer.dto.OvertimDto;
import com.newer.service.OvertimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OvertimServiceImpl implements OvertimService {

    @Autowired
    private OvertimMapper overtimMapper;

    @Override
    public boolean saveOvertim(Overtim overtim) {
        return this.overtimMapper.insertSelective(overtim) > 0 ? true : false;
    }

    @Override
    public PageInfo findOvertimByUpon(OvertimDto overtimDto) {

        PageHelper.startPage(overtimDto.getPage(), overtimDto.getPageSize());

        List list = this.overtimMapper.findOvertimByUpno(overtimDto.getUpno());

//        for (Object obj:list){
//            System.out.println(obj);
//        }

        PageInfo<Overtim> pageInfo = new PageInfo<Overtim>(list);
        return pageInfo;
    }

    @Override
    public Overtim findOvertimByKey(Integer overtimid) {
        return this.overtimMapper.findOvertimByKey(overtimid);
    }
}
