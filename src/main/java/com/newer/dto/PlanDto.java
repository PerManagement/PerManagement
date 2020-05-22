package com.newer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 谢海鸿
 * @Date 2020-05-07 22:40
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlanDto extends PageDto {
    private Integer taskid;
}
