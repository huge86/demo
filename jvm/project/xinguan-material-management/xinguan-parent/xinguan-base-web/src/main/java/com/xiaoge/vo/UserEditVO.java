package com.xiaoge.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UserEditVO {
    private Long id;

    private String username;

    private String nickname;

    private String email;

    private String phoneNumber;

    private Integer sex;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy年MM月dd日")
    private Date birth;

    private Long departmentId;

}
