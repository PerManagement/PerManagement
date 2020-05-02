package com.newer.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AfficheDto extends PageDto {
    public Integer userid;
    public Date releasetime;
}
