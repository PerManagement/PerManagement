package com.newer.service;

import com.github.pagehelper.PageInfo;
import com.newer.domain.Evection;
import com.newer.dto.PageDto;

import java.util.Date;

/**
 * 出差业务层
 */
public interface EvectionService {
    public PageInfo<Evection> findlhl(PageDto dto);
    public PageInfo<Evection> findAal2(PageDto dto);
    public int save(Evection evection);
    public int updateState(Integer evectionid);
   public int updateState2(Integer evectionid);
    public int updatelhl(Integer userid,Integer evectionid);
    public int updateState3(Integer evectionid,Integer approver ,String remark);
    public int updatelhl2(Integer evectionid, Date evectiondate,String evectionaddress, String evectionreason, Date stopevection);
}
