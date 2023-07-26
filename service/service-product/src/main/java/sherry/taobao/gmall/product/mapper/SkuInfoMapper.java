package sherry.taobao.gmall.product.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import sherry.taobao.gmall.model.product.SkuInfo;

/**
 * @Description:
 * @Author: SHERRY
 * @email: <a href="mailto:SherryTh743779@gmail.com">TianHai</a>
 * @Date: 2023/7/31 16:44
 */
@Mapper
public interface SkuInfoMapper extends BaseMapper<SkuInfo> {
    IPage<SkuInfo> getPage(Page<SkuInfo> pageParam, QueryWrapper<SkuInfo> queryWrapper);
}

