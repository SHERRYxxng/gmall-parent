package sherry.taobao.gmall.list.service;

import sherry.taobao.gmall.model.list.SearchParam;
import sherry.taobao.gmall.model.list.SearchResponseVo;

import java.io.IOException;

public interface SearchService {
    /**
     * 商品的上架
     * @param skuId
     */
    void upperGoods(Long skuId);

    /**
     * 商品下架
     * @param skuId
     */
    void lowerGoods(Long skuId);

    /**
     * 更新商品的热度排名
     * @param skuId
     */
    void incrHotScore(Long skuId);
    /**
     * @Description : 查询结果封装
     * @Date 2023/8/8 16:23
     * @Param [searchParam]
     * @return sherry.taobao.gmall.model.list.SearchResponseVo
     * @Author SHERRY
     */

    SearchResponseVo search(SearchParam searchParam) throws IOException;

}
