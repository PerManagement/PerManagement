
        package com.newer.service;

import com.github.pagehelper.PageInfo;
import com.newer.domain.Affiche;
import com.newer.dto.AfficheDto;
import com.newer.dto.PageDto;

import java.util.List;

/**
 * 公告模块业务层
 * 2020-05-02
 * 陈良吉
 */
public interface AfficheService {
    public PageInfo<Affiche> findAffiches(AfficheDto afficheDto);

    public boolean saveAffiche(Affiche affiche);

    public Affiche findAfficheByDate();
}

