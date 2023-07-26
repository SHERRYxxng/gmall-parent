package sherry.taobao.gmall.product.service.impl;

import com.alibaba.nacos.common.utils.CollectionUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import sherry.taobao.gmall.model.product.*;
import sherry.taobao.gmall.product.mapper.*;
import sherry.taobao.gmall.product.service.SkuManageService;


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
    @Autowired
    private SkuInfoMapper skuInfoMapper;

    @Override
    public List<SpuSaleAttr> getSpuSaleAttrList(Long spuId) {
        return spuSaleAttrMapper.selectSpuSaleAttrList(spuId);
    }
    @Override
    public IPage<SkuInfo> getPage(Page<SkuInfo> pageParam) {
        QueryWrapper<SkuInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        IPage<SkuInfo> page = skuInfoMapper.getPage(pageParam, queryWrapper);
        return page;
    }

    @Override
    @Transactional
    public void onSale(Long skuId) {
        // 更改销售状态
        SkuInfo skuInfoUp = new SkuInfo();
        skuInfoUp.setId(skuId);
        skuInfoUp.setIsSale(1);
        skuInfoMapper.updateById(skuInfoUp);
    }

    @Override
    @Transactional
    public void cancelSale(Long skuId) {
        // 更改销售状态
        SkuInfo skuInfoUp = new SkuInfo();
        skuInfoUp.setId(skuId);
        skuInfoUp.setIsSale(0);
        skuInfoMapper.updateById(skuInfoUp);
    }

}
