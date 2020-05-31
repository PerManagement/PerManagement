package com.newer.service;

import com.github.pagehelper.PageInfo;
import com.newer.domain.EvectionAccount;
import com.newer.dto.PageDto;

public interface EvectionAccountService {
    public PageInfo<EvectionAccount> findAal(PageDto dto);
    public int save(EvectionAccount evectionaccount);


}
