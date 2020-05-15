package com.newer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 谢海鸿
 * @Date 2020-05-06 19:38
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskDto extends PageDto {
    private String begindate;
    private String enddate;
}
