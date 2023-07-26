package sherry.taobao.gmall.product.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sherry.taobao.gmall.model.product.BaseAttrInfo;
import sherry.taobao.gmall.model.product.SkuInfo;
import sherry.taobao.gmall.model.product.SpuPoster;
import sherry.taobao.gmall.model.product.SpuSaleAttr;
import sherry.taobao.gmall.product.service.ManageService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: SHERRY
 * @email: <a href="mailto:SherryTh743779@gmail.com">TianHai</a>
 * @Date: 2023/7/31 18:23
 */
@RestController
@RequestMapping("api/product")
public class ProductApiController {

    @Autowired
    private ManageService manageService;
    /**
     * 根据skuId获取sku信息
     *
     * @param skuId
     * @return
     */
    @GetMapping("inner/getSkuInfo/{skuId}")
    public SkuInfo getAttrValueList(@PathVariable("skuId") Long skuId) {
        SkuInfo skuInfo = manageService.getSkuInfo(skuId);
        return skuInfo;
    }

    /**
     * 根据spuId，skuId 查询销售属性集合
     * @param skuId
     * @param spuId
     * @return
     */
    @GetMapping("inner/getSpuSaleAttrListCheckBySku/{skuId}/{spuId}")
    public List<SpuSaleAttr> getSpuSaleAttrListCheckBySku(@PathVariable("skuId") Long skuId, @PathVariable("spuId") Long spuId){
        return manageService.getSpuSaleAttrListCheckBySku(skuId, spuId);
    }
    /**
     * 根据spuId 查询map 集合属性
     * @param spuId
     * @return
     */
    @GetMapping("inner/getSkuValueIdsMap/{spuId}")
    public Map getSkuValueIdsMap(@PathVariable("spuId") Long spuId){
        return manageService.getSkuValueIdsMap(spuId);
    }
    /**
     * 通过skuId 集合来查询数据
     * @param skuId
     * @return
     */
    @GetMapping("inner/getAttrList/{skuId}")
    public List<BaseAttrInfo> getAttrList(@PathVariable("skuId") Long skuId){
        return manageService.getAttrList(skuId);
    }
    //  根据spuId 获取海报数据
    @GetMapping("inner/findSpuPosterBySpuId/{spuId}")
    public List<SpuPoster> findSpuPosterBySpuId(@PathVariable Long spuId){
        return manageService.findSpuPosterBySpuId(spuId);
    }
}
