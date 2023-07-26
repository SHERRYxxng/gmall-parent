package sherry.taobao.gmall.product.client;

import org.springframework.stereotype.Component;
import sherry.taobao.gmall.model.product.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Component
public class ProductDegradeFeignClient implements ProductFeignClient {
    @Override
    public List<BaseAttrInfo> getAttrList(Long skuId) {
        return null;
    }

    @Override
    public Map<String, String> getSkuValueIdsMap(Long spuId) {
        return null;
    }

    @Override
    public List<SpuSaleAttr> getSpuSaleAttrListCheckBySku(Long skuId, Long spuId) {
        return null;
    }

    @Override
    public BaseCategoryView getCategoryView(Long category3Id) {
        return null;
    }

    @Override
    public List<SpuPoster> findSpuPosterBySpuId(Long spuId) {
        return null;
    }

    @Override
    public BigDecimal getSkuPrice(Long skuId) {
        return null;
    }

    @Override
    public SkuInfo getSkuInfo(Long skuId) {
        return null;
    }
}
