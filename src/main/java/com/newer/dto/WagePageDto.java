package com.newer.dto;

import lombok.*;

import java.util.Date;
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WagePageDto {
    private int page=1;
    private int pageSize;
    private Date beginDate;
    private Date endDate;
}
