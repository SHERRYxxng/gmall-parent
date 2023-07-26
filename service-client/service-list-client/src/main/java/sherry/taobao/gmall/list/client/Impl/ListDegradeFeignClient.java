package sherry.taobao.gmall.list.client.Impl;

import org.springframework.stereotype.Component;
import sherry.taobao.gmall.common.result.Result;
import sherry.taobao.gmall.list.client.ListFeignClient;
import sherry.taobao.gmall.model.list.SearchParam;

/**
 * @Description:
 * @Author: SHERRY
 * @email: <a href="mailto:SherryTh743779@gmail.com">TianHai</a>
 * @Date: 2023/8/7 19:41
 */
@Component
public class ListDegradeFeignClient implements ListFeignClient {
    @Override
    public Result list(SearchParam searchParam) {
        return Result.fail();
    }

    @Override
    public Result upperGoods(Long skuId) {
        return null;
    }

    @Override
    public Result lowerGoods(Long skuId) {
        return null;
    }

    @Override
    public Result incrHotScore(Long skuId) {
        return null;
    }
}
