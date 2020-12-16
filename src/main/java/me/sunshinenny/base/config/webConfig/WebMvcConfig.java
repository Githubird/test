package me.sunshinenny.base.config.webConfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author 刘刚
 * @author sunshinenny
 */
@Slf4j
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    // 注入拦截器
    @Resource
    private CorsInterceptor corsInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 跨域拦截器需放在最上面
        log.info("跨域请求已开放");
        registry.addInterceptor(corsInterceptor)
                // 这句话是用来配置访问权限的，开发时候为了防止跨域默认会开放全部请求，生产环境请自行斟酌
                .addPathPatterns("/**");
    }
}