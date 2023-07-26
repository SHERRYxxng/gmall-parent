package sherry.taobao.gmall.product.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sherry.taobao.gmall.common.result.Result;
import sherry.taobao.gmall.model.product.SpuImage;
import sherry.taobao.gmall.product.service.ManageService;

import java.util.List;

/**
 * @Description:
 * @Author: SHERRY
 * @email: <a href="mailto:SherryTh743779@gmail.com">TianHai</a>
 * @Date: 2023/7/28 19:28
 */
@Api(tags = "商品SKU接口")
@RestController
@RequestMapping("admin/product")
public class SkuManageController {

    @Autowired
    private ManageService manageService;

    /**
     * 根据spuId 查询spuImageList
     * @param spuId
     * @return
     */
//    @GetMapping("spuImageList/{spuId}")
//    public Result<List<SpuImage>> getSpuImageList(@PathVariable("spuId") Long spuId) {
//        List<SpuImage> spuImageList = manageService.getSpuImageList(spuId);
//        return Result.ok(spuImageList);
//    }
}
