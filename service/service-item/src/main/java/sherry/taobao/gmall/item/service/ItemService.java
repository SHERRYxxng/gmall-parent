package sherry.taobao.gmall.item.service;

import java.util.Map;

/**
 * @Description:
 * @Author: SHERRY
 * @email: <a href="mailto:SherryTh743779@gmail.com">TianHai</a>
 * @Date: 2023/7/31 18:21
 */
public interface ItemService {

    /**
     * 查询商品详情
     * @param skuId
     * @return
     */
    Map<String, Object> getItem(Long skuId);
}
