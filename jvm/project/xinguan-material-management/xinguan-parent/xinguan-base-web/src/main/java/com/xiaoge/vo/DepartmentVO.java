package com.xiaoge.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class DepartmentVO {

    private Long id;

    private String name;

    private String phone;

    private String address;

    private Long mgrId;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date createTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date modifiedTime;

    /** 系主任的名字*/
    private String mgrName;

    /** 部门内人数**/
    private int total;

}
