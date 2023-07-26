package sherry.taobao.gmall.product.client;

/**
 * @Description:
 * @Author: SHERRY
 * @email: <a href="mailto:SherryTh743779@gmail.com">TianHai</a>
 * @Date: 2023/8/2 10:08
 */

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sherry.taobao.gmall.model.product.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@FeignClient(value = "service-product",fallback = ProductDegradeFeignClient.class)
public interface ProductFeignClient {



    /**
     * //api/product/inner/getAttrList/{skuId}
     * 根据skuId 获取平台属性数据
     * @param skuId
     * @return
     */
    @GetMapping("/api/product/inner/getAttrList/{skuId}")
    public List<BaseAttrInfo> getAttrList(@PathVariable Long skuId);


    /**
     * /api/product/inner/getSkuValueIdsMap/{spuId}
     * 商品切换关系查询
     * @param spuId
     * @return
     */
    @GetMapping("/api/product/inner/getSkuValueIdsMap/{spuId}")
    public Map<String,String> getSkuValueIdsMap(@PathVariable Long spuId);


    /**
     * /api/product/inner/getSpuSaleAttrListCheckBySku/{skuId}/{spuId}
     * 根据spuId,skuId 获取销售属性数据
     * @param skuId
     * @param spuId
     * @return
     */
    @GetMapping("/api/product/inner/getSpuSaleAttrListCheckBySku/{skuId}/{spuId}")
    public List<SpuSaleAttr> getSpuSaleAttrListCheckBySku(@PathVariable Long skuId,
                                                          @PathVariable Long spuId);


    /**
     * //api/product/inner/getCategoryView/{category3Id}
     * 根据三级分类id获取分类信息
     * @param category3Id
     * @return
     */
    @GetMapping("/api/product/inner/getCategoryView/{category3Id}")
    public BaseCategoryView getCategoryView(@PathVariable Long category3Id);

    /**
     * 根据spuId 获取海报数据
     * /api/product/inner/findSpuPosterBySpuId/{spuId}
     * @param spuId
     * @return
     */
    @GetMapping("/api/product/inner/findSpuPosterBySpuId/{spuId}")
    public List<SpuPoster> findSpuPosterBySpuId(@PathVariable Long spuId);


    /**
     * /api/product/inner/getSkuPrice/{skuId}
     * 实时价格查询
     * @param skuId
     * @return
     */
    @GetMapping("/api/product/inner/getSkuPrice/{skuId}")
    public BigDecimal getSkuPrice(@PathVariable Long skuId);

    /**
     * /api/product/inner/getSkuInfo/{skuId}
     * 根据skuId获取SkuInfo
     * @param skuId
     * @return
     */
    @GetMapping("/api/product/inner/getSkuInfo/{skuId}")
    public SkuInfo getSkuInfo(@PathVariable Long skuId);

}
