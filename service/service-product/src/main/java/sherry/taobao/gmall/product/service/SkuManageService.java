package sherry.taobao.gmall.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import sherry.taobao.gmall.model.product.SkuInfo;
import sherry.taobao.gmall.model.product.SpuInfo;
import sherry.taobao.gmall.model.product.SpuSaleAttr;

import java.util.List;

/**
 * @Description:
 * @Author: SHERRY
 * @email: <a href="mailto:SherryTh743779@gmail.com">TianHai</a>
 * @Date: 2023/7/28 19:34
 */
public interface SkuManageService {
    List<SpuSaleAttr> getSpuSaleAttrList(Long spuId);
    /**
     * SKU分页列表
     * @param pageParam
     * @return
     */
    IPage<SkuInfo> getPage(Page<SkuInfo> pageParam);
    /**
     * 商品上架
     * @param skuId
     */
    void onSale(Long skuId);

    /**
     * 商品下架
     * @param skuId
     */
    void cancelSale(Long skuId);



}
