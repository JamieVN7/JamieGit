package com.example.login.service.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDaoImpl<T> extends SqlSessionDaoSupport {
    public static final String UPDATE_SELECTIVE = "updateByPrimaryKeySelective";
    public static final String INSERT_SELECTIVE = "insertSelective";
    public static final String DELETE_BY_PRIMARY_KEY = "deleteByPrimaryKey";

    @Autowired
    private SqlSessionTemplate sessionTemplate;

    public SqlSessionTemplate getSessionTemplate(){
        return sessionTemplate;
    }

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    public SqlSession getSqlSession(){
        return super.getSqlSession();
    }

    public String getStatement(String sqlId){
        //获取sql全路径位置的前缀
        String prefixName = this.getClass().getName();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(prefixName).append(".").append(sqlId);
        return stringBuilder.toString();
    }

    //按需更新
    public Integer updateSelective(T request){
        return getSqlSession().update(getStatement(UPDATE_SELECTIVE), request);
    }

    //插入
    public Integer insertSelective(T request){
        return getSqlSession().insert(getStatement(INSERT_SELECTIVE), request);
    }

    //删除
    public Integer deleteByPrimaryKey(Integer id){
        return getSqlSession().delete(getStatement(DELETE_BY_PRIMARY_KEY), id);
    }
}
