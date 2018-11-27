package com.example.login.api.serviceApi;

import com.example.login.api.request.LoginRequest;
import com.example.login.api.vo.LoginVo;
import com.example.login.util.Urls;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface LoginApi {
    @RequestMapping(value = Urls.userSelectAll, method = RequestMethod.POST)
    List<LoginVo> selectAll(@RequestBody LoginRequest request);

    @RequestMapping(value = Urls.userSelectOne, method = RequestMethod.POST)
    LoginVo selectOne(@RequestBody LoginRequest request);

    @RequestMapping(value = Urls.userUpdate, method = RequestMethod.POST)
    Integer update(@RequestBody LoginRequest request);

    @RequestMapping(value = Urls.userInsert, method = RequestMethod.POST)
    Integer insert(@RequestBody LoginRequest request);

    @RequestMapping(value = Urls.userDelete, method = RequestMethod.POST)
    Integer delete(@RequestBody LoginRequest loginRequest);

    @RequestMapping(value = Urls.userCheck, method = RequestMethod.POST)
    Integer userCheck(@RequestBody LoginRequest request, HttpSession session);

}
