package sherry.taobao.gmall.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sherry.taobao.gmall.model.product.BaseAttrInfo;

import java.util.List;

/**
 * @Description:
 * @Author: SHERRY
 * @email: <a href="mailto:SherryTh743779@gmail.com">TianHai</a>
 * @Date: 2023/7/28 18:40
 */
@Mapper
public interface BaseAttrInfoMapper extends BaseMapper<BaseAttrInfo> {
    List<BaseAttrInfo> selectBaseAttrInfoList(Long category1Id, Long category2Id, Long category3Id);
    /**
     *
     * @param skuId
     */
    List<BaseAttrInfo> selectBaseAttrInfoListBySkuId(@Param("skuId")Long skuId);

}

