package com.newer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Create by 何辉
 * 2020/5/27 16:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePowerDto {
    private Integer[] integers;
    private Integer userid;
}
