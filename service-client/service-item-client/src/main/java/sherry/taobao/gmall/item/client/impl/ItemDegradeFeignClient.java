package sherry.taobao.gmall.item.client.impl;


import org.springframework.stereotype.Component;
import sherry.taobao.gmall.common.result.Result;
import sherry.taobao.gmall.item.client.ItemFeignClient;

@Component
public class ItemDegradeFeignClient implements ItemFeignClient {
    @Override
    public Result item(Long skuId) {
        return Result.fail();
    }
}
