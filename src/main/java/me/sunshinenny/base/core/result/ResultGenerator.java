package me.sunshinenny.base.core.result;

import lombok.Data;

/**
 * 接口数据请求统一响应数据结构
 */
@Data
public class ResultGenerator<T> {
    private boolean isok;  //请求是否处理成功
    private int code; //请求响应状态码
    private String message;  //请求结果描述信息
    private T data; //请求结果数据（通常用于查询操作）

    private ResultGenerator() {
    }

    //请求出现异常时的响应数据封装
    public static ResultGenerator error(CustomException e) {
        ResultGenerator resultBean = new ResultGenerator();
        resultBean.setIsok(false);
        resultBean.setCode(e.getCode());
        resultBean.setMessage(e.getMessage());
        return resultBean;
    }

    //请求出现异常时的响应数据封装
    public static ResultGenerator error(CustomExceptionType customExceptionType,
                                        String errorMessage) {
        ResultGenerator resultBean = new ResultGenerator();
        resultBean.setIsok(false);
        resultBean.setCode(customExceptionType.getCode());
        resultBean.setMessage(errorMessage);
        return resultBean;
    }

    //请求成功的响应，不带查询数据（用于删除、修改、新增接口）
    public static ResultGenerator success() {
        ResultGenerator resultGenerator = new ResultGenerator();
        resultGenerator.setIsok(true);
        resultGenerator.setCode(200);
        resultGenerator.setMessage("请求响应成功!");
        return resultGenerator;
    }

    //请求成功的响应，带有查询数据（用于数据查询接口）
    public static ResultGenerator success(Object obj) {
        ResultGenerator resultGenerator = new ResultGenerator();
        resultGenerator.setIsok(true);
        resultGenerator.setCode(200);
        resultGenerator.setMessage("请求响应成功!");
        resultGenerator.setData(obj);
        return resultGenerator;
    }

    //请求成功的响应，带有查询数据（用于数据查询接口）
    public static ResultGenerator success(Object obj, String message) {
        ResultGenerator resultGenerator = new ResultGenerator();
        resultGenerator.setIsok(true);
        resultGenerator.setCode(200);
        resultGenerator.setMessage(message);
        resultGenerator.setData(obj);
        return resultGenerator;
    }


}