package sherry.taobao.gmall.common.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 全局统一返回结果类
 *
 */
@Data
@ApiModel(value = "全局统一返回结果")
public class Result<T> {

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private T data;

    public Result(){}

    //工厂方法,构建当前类对象,传入数据data,返回数据
    protected static <T> Result<T> build(T data) {
        Result<T> result = new Result<T>();
        if (data != null)
            result.setData(data);
        return result;
    }
    /***
     * @Description: 工厂方法,构建当前类对象,传入数据data,和返回状态码,<br>
     * 传入接口调用返回的数据可以是任意类型,和一个枚举类里面有状态码和代码信息
     * @Date 2023/7/24 21:04
     * @Param [body, resultCodeEnum]
     * @return sherry.taobao.gmall.common.result.Result<T>
     * @Author SHERRY
     */
    public static <T> Result<T> build(T body, ResultCodeEnum resultCodeEnum) {
        Result<T> result = build(body);
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }
    /***
     * @Description: 1.常用的场景有表示接口调用成功但不需要返回具体数据<br>
     *               2.删除更新操作成功 <br>
     *               3.验证码修改密码等只需要知道操作是否承购不需要其他复杂的数据 <br>
     * @Date 2023/7/24 21:02
     * @Param []
     * @return sherry.taobao.gmall.common.result.Result<T>
     * @Author SHERRY
     */
    public static<T> Result<T> ok(){
        return Result.ok(null);
    }

    /**
     * @Description:常用场景有以下这些<br>
     * 1.查询操作成功并返回数据<br>
     * 2.登录成功并返回用户信息<br>
     * 3.获取资源成功并返回内容<br>
     * 4.数据提交成功后返回结果<br>
     * @Date 2023/7/24 20:44
     * @param data
     * @param <T>
     * @return
     * @Author SHERRY
     */
    public static<T> Result<T> ok(T data){
        Result<T> result = build(data);
        return build(data, ResultCodeEnum.SUCCESS);
    }
    //返回一个操作失败的 Result 对象，但不包含数据。
    //实际上是调用了下面的 fail(T data) 方法，并传入 null 作为数据。
    //在这个例子中我个人理解是区分有数据,这里面是前端没有调用接口没有传入数据返回一个空
    public static<T> Result<T> fail(){
        return Result.fail(null);
    }

    /***
     * @Description 传入一个data返回一个操作失败的 Result 对象，在里面存入了数据,并且枚举类状态码为FAIL(201, "失败"),<br>
     * //前端传入了数据返回一个错误,区分是否传入了数据看是前端的问题还是后端的问题
     * @Date 2023/7/24 21:16
     * @Param [data]
     * @return sherry.taobao.gmall.common.result.Result<T>
     * @Author SHERRY
     */
    public static<T> Result<T> fail(T data){
        Result<T> result = build(data);
        return build(data, ResultCodeEnum.FAIL);
    }
    //用于链式调用添加自定义异常信息,返回对象本身
    public Result<T> message(String msg){
        this.setMessage(msg);
        return this;
    }
    //用于链式调用添加自定义代码,返回对象本身
    public Result<T> code(Integer code){
        this.setCode(code);
        return this;
    }
    //用于检查当前 Result 对象的状态码是否表示操作成功。它通过比较状态码是否等于 ResultCodeEnum.SUCCESS
    // 枚举类型中表示成功的状态码，来判断是否操作成功。如果状态码与成功状态码相同，则返回 true，否则返回 false。
    public boolean isOk() {
        if(this.getCode().intValue() == ResultCodeEnum.SUCCESS.getCode().intValue()) {
            return true;
        }
        return false;
    }
}
