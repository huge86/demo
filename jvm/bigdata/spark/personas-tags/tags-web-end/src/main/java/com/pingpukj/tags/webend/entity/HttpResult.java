package com.pingpukj.tags.webend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HttpResult<T> {
    private Integer code;
    private String msg;
    private T data;
}
