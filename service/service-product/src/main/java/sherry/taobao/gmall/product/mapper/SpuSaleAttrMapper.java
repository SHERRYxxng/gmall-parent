package sherry.taobao.gmall.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import sherry.taobao.gmall.model.product.SpuSaleAttr;

import java.util.List;

/**
 * @Description:
 * @Author: SHERRY
 * @email: <a href="mailto:SherryTh743779@gmail.com">TianHai</a>
 * @Date: 2023/7/28 19:36
 */
@Mapper
public interface SpuSaleAttrMapper extends BaseMapper<SpuSaleAttr> {
    // 根据spuId 查询销售属性集合
    List<SpuSaleAttr> selectSpuSaleAttrList(Long spuId);

    List<SpuSaleAttr> selectSpuSaleAttrListCheckBySku(Long skuId, Long spuId);
}

