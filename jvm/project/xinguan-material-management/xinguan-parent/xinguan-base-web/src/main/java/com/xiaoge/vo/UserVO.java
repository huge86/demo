package com.xiaoge.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UserVO{

    private String username;

    private String nickname;

    private String email;

    private Integer sex;

    private Long departmentId;

}
