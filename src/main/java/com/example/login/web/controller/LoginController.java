package com.example.login.web.controller;

import com.example.login.api.request.JsonResult;
import com.example.login.api.request.LoginRequest;
import com.example.login.api.serviceApi.LoginApi;
import com.example.login.api.vo.LoginVo;
import com.example.login.web.filter.LoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login/")
public class LoginController {

    @Value("${cdn.url}")
    private String cdn;

    @Autowired
    private LoginApi loginApi;

  /**
     * 登录页面
     * @param model
     * @return
     */
    @RequestMapping("/index/")
    public String index(Model model){
        model.addAttribute("cdn", cdn);
        return "index";
    }

    /**
     * 成功登录页面
     * @param userName：获取session保存的用户名
     * @param model
     * @return
     */
    @RequestMapping("/checkIn/")
    public String checkIn(@SessionAttribute(LoginFilter.SESSION_KEY) String userName, Model model){
        model.addAttribute("userName", userName);
        model.addAttribute("cdn", cdn);
        return "checkIn";
    }


    /**
     *  检查用户名和密码是否正确（1：正确，0：错误）
     * @param request
     * @param session
     * @return
     */
    @PostMapping("/check/")
    @ResponseBody
    public JsonResult loginCheck(LoginRequest request, HttpSession session){
        Integer result = loginApi.userCheck(request, session);
        if (result == 0){
            return JsonResult.fail();
        }
        return JsonResult.success();
    }

    /**
     * 用户注册页
     * @return
     */
    @RequestMapping("/regist/")
    public String regist(Model model){
        model.addAttribute("cdn", cdn);
        return "regist";
    }

    /**
     * 用户注册逻辑
     * @param request
     * @return
     */
    @RequestMapping("/regist-insert/")
    @ResponseBody
    public JsonResult userInsert(LoginRequest request){
        Integer result = loginApi.insert(request);
        if (result == 0){
            return JsonResult.fail("请求参数有误");
        }
        else if (result == -1){
            return JsonResult.fail("该用户名已注册");
        }
        return JsonResult.success();
    }

    /**
     * 删除用户信息
     * @param request
     * @return
     */
    @RequestMapping("/delete/")
    @ResponseBody
    public Integer delete(LoginRequest request){
        return loginApi.delete(request);
    }

}