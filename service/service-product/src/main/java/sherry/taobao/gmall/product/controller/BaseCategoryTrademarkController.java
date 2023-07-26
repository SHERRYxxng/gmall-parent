package sherry.taobao.gmall.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sherry.taobao.gmall.common.result.Result;
import sherry.taobao.gmall.model.product.BaseTrademark;
import sherry.taobao.gmall.model.product.CategoryTrademarkVo;
import sherry.taobao.gmall.product.service.BaseCategoryTrademarkService;

import java.util.List;

/**
 * @Description:
 * @Author: SHERRY
 * @email: <a href="mailto:SherryTh743779@gmail.com">TianHai</a>
 * @Date: 2023/7/28 19:51
 */
@RestController
@RequestMapping("admin/product/baseCategoryTrademark")
public class BaseCategoryTrademarkController {

    @Autowired
    private BaseCategoryTrademarkService baseCategoryTrademarkService;


    @PostMapping("save")
    public Result save(@RequestBody CategoryTrademarkVo categoryTrademarkVo){
        //  保存方法
        baseCategoryTrademarkService.save(categoryTrademarkVo);
        return Result.ok();
    }

    @DeleteMapping("remove/{category3Id}/{trademarkId}")
    public Result remove(@PathVariable Long category3Id, @PathVariable Long trademarkId){
        //  调用服务层方法
        baseCategoryTrademarkService.removeBaseCategoryTrademarkById(category3Id, trademarkId);
        return Result.ok();
    }

    @GetMapping("findTrademarkList/{category3Id}")
    public Result findTrademarkList(@PathVariable Long category3Id){
        //  select * from base_trademark
        List<BaseTrademark> list = baseCategoryTrademarkService.findTrademarkList(category3Id);
        //  返回
        return Result.ok(list);
    }

    @GetMapping("findCurrentTrademarkList/{category3Id}")
    public Result findCurrentTrademarkList(@PathVariable Long category3Id){
        List<BaseTrademark> list = baseCategoryTrademarkService.findCurrentTrademarkList(category3Id);
        //  返回
        return Result.ok(list);
    }
}
