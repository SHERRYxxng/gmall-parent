package sherry.taobao.gmall.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import sherry.taobao.gmall.model.product.SkuSaleAttrValue;

import java.util.List;
import java.util.Map;

@Mapper
public interface SkuSaleAttrValueMapper  extends BaseMapper<SkuSaleAttrValue> {
    /**
     *  商品切换关系查询
     * @param spuId
     * @return
     */
    List<Map> selectSkuValueIdMap(Long spuId);
}
