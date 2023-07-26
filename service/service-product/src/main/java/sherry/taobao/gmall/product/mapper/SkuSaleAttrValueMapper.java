package sherry.taobao.gmall.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import sherry.taobao.gmall.model.product.SkuSaleAttrValue;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: SHERRY
 * @email: <a href="mailto:SherryTh743779@gmail.com">TianHai</a>
 * @Date: 2023/7/31 16:44
 */
@Mapper
public interface SkuSaleAttrValueMapper extends BaseMapper<SkuSaleAttrValue> {
    List<Map> selectSaleAttrValuesBySpu(Long spuId);
}
