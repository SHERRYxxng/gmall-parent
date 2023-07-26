package sherry.taobao.gmall.product.service.impl;

import com.alibaba.nacos.common.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import sherry.taobao.gmall.model.product.*;
import sherry.taobao.gmall.product.mapper.*;
import sherry.taobao.gmall.product.service.SkuManageService;
import sherry.taobao.gmall.product.service.SpuImageService;

import java.util.List;

/**
 * @Description:
 * @Author: SHERRY
 * @email: <a href="mailto:SherryTh743779@gmail.com">TianHai</a>
 * @Date: 2023/7/28 19:35
 */
public class SkuManageImpl  implements SkuManageService {

    @Autowired
    private SpuSaleAttrMapper spuSaleAttrMapper;


    @Override
    public List<SpuSaleAttr> getSpuSaleAttrList(Long spuId) {
        return spuSaleAttrMapper.selectSpuSaleAttrList(spuId);
    }

}
