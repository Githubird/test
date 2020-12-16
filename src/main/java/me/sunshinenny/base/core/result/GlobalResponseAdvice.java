package me.sunshinenny.base.core.result;

import me.sunshinenny.base.core.constParam.PathWhiteList;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author 陶宇豪
 */
@Component
@ControllerAdvice
public class GlobalResponseAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        // 因为统一封装了返回值
        // Swagger的对象会被误伤
        // 所以此处需要进行特殊化处理
        for (String accessPath : PathWhiteList.SWAGGERWHITELIST) {
            if (serverHttpRequest.getURI().getPath().contains(accessPath)) {
                return body;
            }
        }
        //如果响应结果是JSON数据类型
        if (mediaType.equalsTypeAndSubtype(
                MediaType.APPLICATION_JSON)) {
            if (body instanceof ResultGenerator) {
                ResultGenerator resultGenerator = (ResultGenerator) body;
                //999 不是标准的HTTP状态码，特殊处理
                if (resultGenerator.getCode() != 999) {
                    serverHttpResponse.setStatusCode(HttpStatus.valueOf(
                            resultGenerator.getCode()
                    ));
                }
                return body;
            } else {
                serverHttpResponse.setStatusCode(HttpStatus.OK);
                return ResultGenerator.success(body);
            }

        }
        return body;
    }
}