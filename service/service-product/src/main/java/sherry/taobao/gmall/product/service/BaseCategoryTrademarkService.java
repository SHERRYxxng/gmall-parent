package sherry.taobao.gmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sherry.taobao.gmall.model.product.BaseCategoryTrademark;
import sherry.taobao.gmall.model.product.BaseTrademark;
import sherry.taobao.gmall.model.product.CategoryTrademarkVo;

import java.util.List;

/**
 * @Description:
 * @Author: SHERRY
 * @email: <a href="mailto:SherryTh743779@gmail.com">TianHai</a>
 * @Date: 2023/7/28 19:50
 */
public interface BaseCategoryTrademarkService extends IService<BaseCategoryTrademark> {

    /**
     * 根据三级分类获取品牌
     * @param category3Id
     * @return
     */
    List<BaseTrademark> findTrademarkList(Long category3Id);

    /**
     * 保存分类与品牌关联
     * @param categoryTrademarkVo
     */
    void save(CategoryTrademarkVo categoryTrademarkVo);

    /**
     * 获取当前未被三级分类关联的所有品牌
     * @param category3Id
     * @return
     */
    List<BaseTrademark> findCurrentTrademarkList(Long category3Id);

    /**
     * 删除关联
     * @param category3Id
     * @param trademarkId
     */
    void removeBaseCategoryTrademarkById(Long category3Id, Long trademarkId);
}
