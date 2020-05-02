package com.newer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页条件类
 * 2020-05-02
 * 陈良吉
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageDto {
    private Integer page = 1;
    private Integer pageSize = 3;

    public Integer getPage() {
        if (page < 1) page = 1;
        return page;
    }


}
