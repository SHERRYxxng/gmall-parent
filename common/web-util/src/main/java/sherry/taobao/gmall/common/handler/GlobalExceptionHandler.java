package sherry.taobao.gmall.common.handler;

import sherry.taobao.gmall.common.execption.GmallException;
import sherry.taobao.gmall.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import sherry.taobao.gmall.common.execption.GmallException;
import sherry.taobao.gmall.common.result.Result;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail();
    }

    /**
     * 自定义异常处理方法
     * @param e
     * @return
     */
    @ExceptionHandler(GmallException.class)
    @ResponseBody
    public Result error(GmallException e){
        return Result.fail(e.getMessage());
    }
}
