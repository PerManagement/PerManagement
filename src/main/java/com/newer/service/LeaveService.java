package com.newer.service;

import com.github.pagehelper.PageInfo;
import com.newer.domain.Leave;
import com.newer.dto.PageDto;

import java.util.Date;

/**
 * 请假业务层
 */
public interface LeaveService {
    public PageInfo<Leave> findlhl(PageDto dto);
    public PageInfo<Leave> findlhl2(PageDto dto);
    public PageInfo<Leave> findlhl3(PageDto dto);
    public int save(Leave leave);
    public int updatelhl(Integer userid,Integer leaveid);
    public int updatelhl2(Leave leave);
    public int updateState(Integer leaveid);
    public int updateState2(Integer leaveid);
    public int updateState3(Integer leaveid,Integer approver,String remark);
    public int updateState6(Integer leaveid,Integer approval,String remarks);
    public int updateState5(Integer leaveid);

}
