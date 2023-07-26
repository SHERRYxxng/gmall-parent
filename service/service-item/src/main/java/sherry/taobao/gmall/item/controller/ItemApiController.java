package sherry.taobao.gmall.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sherry.taobao.gmall.common.result.Result;
import sherry.taobao.gmall.item.service.ItemService;

import java.util.Map;

@RestController
@RequestMapping("/api/item")
public class ItemApiController {

    @Autowired
    private ItemService itemService;


    /**
     * /api/item/{skuId}
     * 查询商品详情
     * @param skuId
     * @return
     */
    @GetMapping("/{skuId}")
    public Result item(@PathVariable Long skuId){
        Map<String,Object> resultMap=itemService.getItem(skuId);

        return Result.ok(resultMap);
    }

}
