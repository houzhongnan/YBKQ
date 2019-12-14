

package com.kqxt.springboot.config;

import com.kqxt.springboot.web.interceptor.LoginForAdminInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;



import com.kqxt.springboot.web.interceptor.LoginForAdminInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

/**
 * 拦截器配置
 *//*
*/


@Configuration
public class InterceptorConfig implements WebMvcConfigurer {



/**
 * spring boot2的拦截器中@Resource按传统的SSM无法注入对象，必须在这里创建对象
 * 然后通过 public void addInterceptors(InterceptorRegistry registry) 调用该方法而不是new即可解决方法
 *
 * @return
 */


    @Bean
    public LoginForAdminInterceptor loginForAdminInterceptor() {
        return new LoginForAdminInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //这里可以添加多个拦截器

/**
 * 添加后台登录拦截器
 * 注意：registry.addInterceptor(loginForAdminInterceptor())调用了上面的 public LoginForAdminInterceptor loginForAdminInterceptor()
 * 用以解决 spring boot2的拦截器中@Resource按传统的SSM无法注入对象的问题
 */

        registry.addInterceptor(loginForAdminInterceptor()).addPathPatterns("/backstage/**")
                .excludePathPatterns(new ArrayList<String>() {{
                    add("toLogin");
                    add("login");
                    add("logout");
                }});
    }




/**
 * 不拦截资源文件，包括上传文件夹也可以在这里取消拦截
 *
 * @param registry
 */
@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }


@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")//设置允许跨域的路径
                .allowedOrigins("*")//设置允许跨域请求的域名
                .allowCredentials(true)//是否允许证书 不再默认开启
                .allowedMethods("GET", "POST", "PUT", "DELETE")//设置允许的方法
                .maxAge(3600);//跨域允许时间
    }


}


