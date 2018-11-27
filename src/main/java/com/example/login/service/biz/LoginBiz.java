package com.example.login.service.biz;

import com.example.login.api.entity.LoginUserEntity;
import com.example.login.api.request.LoginRequest;
import com.example.login.api.vo.LoginVo;
import com.example.login.service.dao.LoginUserDao;
import com.example.login.web.filter.LoginFilter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class LoginBiz {
    @Autowired
    private LoginUserDao loginUserDao;

    public List<LoginVo> selectAll(LoginRequest request){
        return loginUserDao.selectAll(request);
    }

    public LoginVo selectOne(LoginRequest request){
        return loginUserDao.selectOne(request);
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer update(LoginRequest request){
        LoginUserEntity entity = new LoginUserEntity();
        BeanUtils.copyProperties(request, entity);
        return loginUserDao.updateSelective(entity);
    }

    //注册逻辑
    @Transactional(rollbackFor = Exception.class)
    public Integer insert(LoginRequest request){
        //无密码或用户名
        if (request.getPassword() == null
                || request.getUserName() == null){
            return 0;
        }
        //看该用户名是否已注册
        List<LoginVo> vos = loginUserDao.selectAll(request);
        if (vos.size() > 0){
            return -1;
        }
        //请求参数
        LoginUserEntity entity = new LoginUserEntity();
        BeanUtils.copyProperties(request, entity);
        //md5加密
        String password = request.getPassword();
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        entity.setPassword(md5Password);

        return loginUserDao.insertSelective(entity);
    }

    //删除记录
    @Transactional(rollbackFor = Exception.class)
    public Integer delete(LoginRequest request){
        Integer status = request.getDelStatus();
        //删除用户名重复的记录
        if (status == 1){
            return loginUserDao.deleteDuplicateUserName(request);
        }
        //根据用户名删除
        else if (status == 2){
            return loginUserDao.deleteByUserName(request);
        }
        //根据id删除
        else {
            return loginUserDao.deleteByPrimaryKey(request.getId());
        }
    }

    public Integer userCheck(LoginRequest request, HttpSession session){
        //请求的用户名或密码为空
        if (request.getUserName() == null || request.getPassword() == null){
            return 0;
        }
        //根据useName查有无对应的数据
        LoginVo loginVo = loginUserDao.selectOne(request);
        if (loginVo == null){
            return 0;
        }
        //md5密文
        String password = loginVo.getPassword();
        //请求的密码
        String requestPassword = DigestUtils.md5DigestAsHex(request.getPassword().getBytes());
        //密码不对
        if (!password.equals(requestPassword)){
            return 0;
        }
        //将userName写入会话
        session.setAttribute(LoginFilter.SESSION_KEY, loginVo.getUserName());

        return 1;
    }
}
