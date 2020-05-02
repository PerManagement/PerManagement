package com.newer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 公告条件类
 * 2020-05-02
 * 陈良吉
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AfficheDto extends PageDto {
    public Integer userid;
    public Date releasetime;
}
