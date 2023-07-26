package sherry.taobao.gmall.item.client;

/**
 * @Description:
 * @Author: SHERRY
 * @email: <a href="mailto:SherryTh743779@gmail.com">TianHai</a>
 * @Date: 2023/8/2 10:33
 */

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sherry.taobao.gmall.common.result.Result;

@FeignClient(value = "service-item",fallback = ItemDegradeFeignClient.class)
public interface ItemFeignClient {


    /**
     *
     *  /api/item/{skuId}
     * 查询商品详情
     * @param skuId
     * @return
     */
    @GetMapping("/api/item/{skuId}")
    Result item(@PathVariable Long skuId);

}
