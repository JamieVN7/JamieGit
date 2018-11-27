package com.example.login.web.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Bean
    public LoginFilter getLoginFilter(){
        return new LoginFilter();
    }

    /**
     * 设置自定义拦截器以及不需要拦截的路径
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(getLoginFilter())
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/static/**",       //静态资源路径
                        "/login/index**/",  //登录页
                        "/login/check/",    //登录校验
                        "/login/select**/", //查询
                        "/login/delete/",   //删除
                        "/login/regist**/"  //注册页以及注册逻辑
                );
        super.addInterceptors(registry);
    }

    /**
     * 配置静态文件路径
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }
}
