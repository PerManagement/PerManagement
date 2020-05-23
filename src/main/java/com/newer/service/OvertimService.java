package com.newer.service;

import com.github.pagehelper.PageInfo;
import com.newer.domain.Overtim;
import com.newer.dto.OvertimDto;

public interface OvertimService {
    public boolean saveOvertim(Overtim overtim);
    public PageInfo findOvertimByUpon(OvertimDto overtimDto);
}
