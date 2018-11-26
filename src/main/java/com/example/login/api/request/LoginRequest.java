package com.example.login.api.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LoginRequest implements Serializable {
    private Integer id;
    private String userName;
    private String password;

    private Integer delStatus;//1:删除名称重复的记录
}
