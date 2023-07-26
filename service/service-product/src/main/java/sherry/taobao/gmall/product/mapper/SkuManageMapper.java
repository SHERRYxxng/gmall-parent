package sherry.taobao.gmall.product.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import sherry.taobao.gmall.model.product.SkuInfo;
import sherry.taobao.gmall.model.product.SpuSaleAttr;

import java.util.List;

/**
 * @Description:
 * @Author: SHERRY
 * @email: <a href="mailto:SherryTh743779@gmail.com">TianHai</a>
 * @Date: 2023/7/28 19:34
 */
@Mapper
public interface SkuManageMapper {

    IPage<SkuInfo> getPage(Page<SkuInfo> pageParam, QueryWrapper<SkuInfo> queryWrapper);
}
