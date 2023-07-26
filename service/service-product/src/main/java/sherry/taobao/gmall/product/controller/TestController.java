package sherry.taobao.gmall.product.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sherry.taobao.gmall.common.result.Result;
import sherry.taobao.gmall.product.service.TestService;

@RestController
@RequestMapping("/admin/product/test")
public class TestController {

    @Autowired
    private TestService testService;


    /**
     * 读锁
     * @return
     */
    @GetMapping("read")
    public Result<String> read(){
        String msg = testService.readLock();

        return Result.ok(msg);
    }


    /**
     * 写锁
     * @return
     */
    @GetMapping("write")
    public Result<String> write(){
        String msg = testService.writeLock();

        return Result.ok(msg);
    }
    /**
     * 测试锁的局限性
     * @return
     */
    @GetMapping("/testLock")
    public Result testLock(){

        testService.testLock();

        return Result.ok();
    }
}
