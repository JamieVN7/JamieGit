package com.example.login.service.dao;

import com.example.login.api.entity.LoginUserEntity;
import com.example.login.api.request.LoginRequest;
import com.example.login.api.vo.LoginVo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class LoginUserDao extends BaseDaoImpl<LoginUserEntity> {
    public List<LoginVo> selectAll(LoginRequest request){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userName", request.getUserName());
        paramMap.put("password", request.getPassword());
        paramMap.put("id", request.getId());
        return getSqlSession().selectList(getStatement("selectAll"), paramMap);
    }

    public LoginVo selectOne(LoginRequest request){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", request.getId());
        paramMap.put("userName", request.getUserName());
        return getSqlSession().selectOne(getStatement("selectOne"), paramMap);
    }

    //删除用户名重复的记录
    public Integer deleteDuplicateUserName(LoginRequest request){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userName", request.getUserName());
        return getSqlSession().delete(getStatement("deleteDuplicateUserName"), paramMap);
    }
}
