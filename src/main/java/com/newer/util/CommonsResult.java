package com.newer.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Create by 何辉
 * 2020/3/28 22:58‘
 * 前后端交互，返回结果集的工具类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonsResult<T> {
    private Integer code;//状态码 404 500 200
    private String message;//描述信息 登录成功
    private T       data;//

    public CommonsResult(Integer code,String message){
          this(code,message,null);
    }
}
