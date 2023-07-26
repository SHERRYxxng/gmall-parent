package sherry.taobao.gmall.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import sherry.taobao.gmall.model.product.SpuSaleAttr;
import sherry.taobao.gmall.product.mapper.SpuImageMapper;
import sherry.taobao.gmall.product.mapper.SpuSaleAttrMapper;
import sherry.taobao.gmall.product.service.SpuSaleAttrService;

import java.util.List;

/**
 * @Description:
 * @Author: SHERRY
 * @email: <a href="mailto:SherryTh743779@gmail.com">TianHai</a>
 * @Date: 2023/7/28 19:40
 */
public class SpuSaleAttrImpl  implements SpuSaleAttrService {
    @Autowired
    private SpuSaleAttrMapper spuSaleAttrMapper;
    @Override
    public List<SpuSaleAttr> getSpuSaleAttrList(Long spuId) {
        return spuSaleAttrMapper.selectSpuSaleAttrList(spuId);
    }

}
