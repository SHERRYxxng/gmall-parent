package sherry.taobao.gmall.item.client;

import org.springframework.stereotype.Component;
import sherry.taobao.gmall.common.result.Result;

/**
 * @Description:
 * @Author: SHERRY
 * @email: <a href="mailto:SherryTh743779@gmail.com">TianHai</a>
 * @Date: 2023/8/2 10:34
 */
@Component
public class ItemDegradeFeignClient implements ItemFeignClient {
    @Override
    public Result item(Long skuId) {
        return Result.fail();
    }
}
