package me.sunshinenny.base.core.result;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class WebExceptionHandler {

    /**
     * 处理程序员主动转换的自定义异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResultGenerator customerException(CustomException e) {
        if (e.getCode() == CustomExceptionType.SYSTEM_ERROR.getCode()) {
            //400异常不需要持久化，将异常信息以友好的方式告知用户就可以
            //TODO 将500异常信息持久化处理，方便运维人员处理
        }
        return ResultGenerator.error(e);
    }

    /**
     * 处理程序员在程序中未能捕获（遗漏的）异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultGenerator exception(Exception e) {
        //TODO 将异常信息持久化处理，方便运维人员处理

        return ResultGenerator.error(new CustomException(
                CustomExceptionType.OTHER_ERROR));
    }

    /**
     * 数据校验失败的时候，会抛出异常BindException或MethodArgumentNotValidException
     * 会使用此方法自动化处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResultGenerator handleBindException(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        return ResultGenerator.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR,
                fieldError.getDefaultMessage()));
    }


    /**
     * 使用org.springframework.util.Assert断言，如果不满足条件就抛出IllegalArgumentException
     * 会使用此方法自动化处理
     * @param ex
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResultGenerator handleBindException(BindException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        return ResultGenerator.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR,
                fieldError.getDefaultMessage()));
    }


}