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

    public void test(){

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
            return JsonResult.fail();
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
        Integer result;
        //删除用户名重复的记录
        if (request.getDelStatus() == 1){
            result = loginApi.deleteDuplicateUserName(request);
        }
        //按照id删除
        else {
            result = loginApi.delete(request);
        }
        return result;
    }

    /**
     * 单条查询
     * @param request
     * @return
     */
    @RequestMapping("/select/")
    @ResponseBody
    public LoginVo select(LoginRequest request){
        return loginApi.selectOne(request);
    }
}