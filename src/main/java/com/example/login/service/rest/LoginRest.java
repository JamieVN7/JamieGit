package com.example.login.service.rest;

import com.example.login.api.request.LoginRequest;
import com.example.login.api.serviceApi.LoginApi;
import com.example.login.api.vo.LoginVo;
import com.example.login.service.biz.LoginBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class LoginRest implements LoginApi {
    @Autowired
    private LoginBiz loginBiz;

    @Override
    public List<LoginVo> selectAll(LoginRequest request) {
        return loginBiz.selectAll(request);
    }

    @Override
    public LoginVo selectOne(LoginRequest request) {
        return loginBiz.selectOne(request);
    }

    @Override
    public Integer update(LoginRequest request) {
        return loginBiz.update(request);
    }

    @Override
    public Integer insert(LoginRequest request) {
        return loginBiz.insert(request);
    }

    @Override
    public Integer delete(LoginRequest request) {
        return loginBiz.delete(request);
    }

    @Override
    public Integer userCheck(LoginRequest request, HttpSession session) {
        return loginBiz.userCheck(request, session);
    }

}
