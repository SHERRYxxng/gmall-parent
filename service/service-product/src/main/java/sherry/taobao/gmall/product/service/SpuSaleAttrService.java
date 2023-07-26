package sherry.taobao.gmall.product.service;

import org.apache.ibatis.annotations.Param;
import sherry.taobao.gmall.model.product.SpuSaleAttr;

import java.util.List;

/**
 * @Description:
 * @Author: SHERRY
 * @email: <a href="mailto:SherryTh743779@gmail.com">TianHai</a>
 * @Date: 2023/7/28 19:40
 */
public interface SpuSaleAttrService {
    /**
     * 根据spuId 查询销售属性集合
     * @param spuId
     * @return
     */
    List<SpuSaleAttr> getSpuSaleAttrList(Long spuId);
    // 根据spuId，skuId 查询销售属性集合

    List<SpuSaleAttr> getSpuSaleAttrListCheckBySku(@Param("skuId") Long skuId, @Param("spuId")Long spuId);
}
