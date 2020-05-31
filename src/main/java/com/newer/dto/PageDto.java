package com.newer.dto;

import com.newer.domain.Plan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 *分页
 * 作者：谢海鸿
 * 时间：2020-05-01 20:00
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageDto {
    private Integer page=1;
    private Integer pageSize=4;
    public Integer getPage() {
        if(page<1) this.page=1;
        return page;
    }
}
