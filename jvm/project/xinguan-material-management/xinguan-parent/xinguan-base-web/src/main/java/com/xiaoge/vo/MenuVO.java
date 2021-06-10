package com.xiaoge.vo;

import lombok.Data;

import java.util.Date;

@Data
public class MenuVO {

    private Long id;

    private Long parentId;

    private String menuName;

    private String url;

    private String icon;

    private Integer type;

    private Long orderNum;

    private Date createTime;

    private Date modifiedTime;

    private boolean disabled;

    private Integer open;

    private String perms;
}
