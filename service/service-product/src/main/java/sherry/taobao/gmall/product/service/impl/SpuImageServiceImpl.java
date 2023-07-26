package sherry.taobao.gmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sherry.taobao.gmall.model.product.SpuImage;
import sherry.taobao.gmall.product.mapper.BaseCategory1Mapper;
import sherry.taobao.gmall.product.mapper.SpuImageMapper;
import sherry.taobao.gmall.product.service.SpuImageService;

import java.util.List;

/**
 * @Description:
 * @Author: SHERRY
 * @email: <a href="mailto:SherryTh743779@gmail.com">TianHai</a>
 * @Date: 2023/7/28 19:25
 */
@Service
public class SpuImageServiceImpl implements SpuImageService {
    @Autowired
    private SpuImageMapper spuImageMapper;
    @Override
    public List<SpuImage> getSpuImageList(Long spuId) {
        QueryWrapper<SpuImage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("spu_id", spuId);
        return spuImageMapper.selectList(queryWrapper);
    }

}
