package me.sunshinenny.base.config.webConfig;

import lombok.extern.slf4j.Slf4j;
import me.sunshinenny.base.util.httpUtil.HttpRequestUtil;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 默认拦截器
 *
 * @author 陶宇豪
 * @date 2019-10-29 8:25 下午
 */
@Component
@Slf4j
public class CorsInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 输出请求链接
        log.info("前端请求URL: {}", request.getRequestURI());
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "86400");
        response.setHeader("Access-Control-Allow-Headers", "*");
        // 定义请求起始时间,用于计算请求耗时
        request.setAttribute("startTime", System.currentTimeMillis());
        // 如果是OPTIONS则结束请求
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            response.setStatus(HttpStatus.NO_CONTENT.value());
            return false;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        long longstartTime = (Long) request.getAttribute("startTime");
        long longendTime = System.currentTimeMillis();
        log.info("操作者:\"{}\",{}请求,耗时{}毫秒,ip={}", HttpRequestUtil.getRequestUserName(request), request.getRequestURI(), longendTime - longstartTime, HttpRequestUtil.getIpAddr(request));
    }

}

