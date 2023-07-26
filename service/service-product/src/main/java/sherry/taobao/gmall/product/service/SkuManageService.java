package sherry.taobao.gmall.product.service;

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

}
