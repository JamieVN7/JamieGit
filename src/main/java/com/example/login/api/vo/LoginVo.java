package com.example.login.api.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LoginVo implements Serializable {
    private Integer id;
    private String userName;
    private String password;
}
