package com.newer.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WagePageDto extends PageDto {
    private String beginDate;
    private String endDate;
    private Integer userId;
}
