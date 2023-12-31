package sherry.taobao.gmall.common.execption;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import sherry.taobao.gmall.common.result.ResultCodeEnum;

/**
 * 自定义全局异常类
 */
@Data
@ApiModel(value = "自定义全局异常类") //这是swagger里面接口文档的注解
public class GmallException extends RuntimeException {

    @ApiModelProperty(value = "异常状态码")  //swagger里面接口文档的属性的注解
    private Integer code;

    /**
     * 通过状态码和错误消息创建异常对象
     * @param message
     * @param code
     */
    public GmallException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    /**
     * 接收枚举类型对象
     * @param resultCodeEnum
     */
    public GmallException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "GuliException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}
