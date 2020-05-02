package com.newer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageDto {

    private Integer page=1;
    private Integer pageSize=4;

    public Integer getPage() {
        if(page<1) page=1;
        return page;
    }
}
