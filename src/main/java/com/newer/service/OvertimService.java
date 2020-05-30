package com.newer.service;

import com.github.pagehelper.PageInfo;
import com.newer.domain.Overtim;
import com.newer.dto.OvertimDto;

import java.util.List;

public interface OvertimService {
    public boolean saveOvertim(Overtim overtim);
    public PageInfo findOvertimByUpon(OvertimDto overtimDto);
    public Overtim findOvertimByKey(Integer overtimid);
    public PageInfo findOvertimByUserId(OvertimDto overtimDto);
    public boolean updateOvertimByUpno(Overtim overtim);
    public boolean updateOvertimByOvertimtype(Overtim overtim);
    public boolean updateOvertimByKey(Overtim overtim);
}
