package sherry.taobao.gmall.product.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sherry.taobao.gmall.common.result.Result;
import sherry.taobao.gmall.model.product.SkuInfo;
import sherry.taobao.gmall.model.product.SpuImage;
import sherry.taobao.gmall.model.product.SpuSaleAttr;
import sherry.taobao.gmall.product.service.ManagerService;

import java.util.List;

@RestController
@RequestMapping("/admin/product")
public class SkuManagerController {

    @Autowired
    private ManagerService managerService;



    /**
     * /admin/product/cancelSale/{skuId}
     * 商品下架
     * @param skuId
     * @return
     */
    @GetMapping("/cancelSale/{skuId}")
    public Result cancelSale(@PathVariable Long skuId){

        managerService.cancelSale(skuId);
        return Result.ok();
    }
    /**
     * /admin/product/onSale/{skuId}
     * 商品上架
     * @param skuId
     * @return
     */
    @GetMapping("/onSale/{skuId}")
    public Result onSale(@PathVariable Long skuId){

        managerService.onSale(skuId);
        return Result.ok();
    }


    /**
     * //admin/product/list/{page}/{limit}
     * 分页sku列表
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/list/{page}/{limit}")
    public Result findSkuByPage(@PathVariable Long page,
                                @PathVariable Long limit){

        //封装
        Page<SkuInfo> skuInfoPage=new Page<>(page,limit);
        //查询
         IPage<SkuInfo> skuInfoIPage= managerService.findSkuListBypage(skuInfoPage);
        return Result.ok(skuInfoIPage);

    }

    /**
     * admin/product/saveSkuInfo
     * 保存sku
     * @param skuInfo
     * @return
     */
    @PostMapping("/saveSkuInfo")
    public Result saveSkuInfo(@RequestBody SkuInfo skuInfo){


            managerService.saveSkuInfo(skuInfo);
        return Result.ok();

    }

    /**
     * 根据spuId 查询销售属性
     * admin/product/spuSaleAttrList/{spuId}
     * @param spuId
     * @return
     */
    @GetMapping("/spuSaleAttrList/{spuId}")
    public Result spuSaleAttrList(@PathVariable Long spuId){

        List<SpuSaleAttr> spuSaleAttrList=managerService.spuSaleAttrList(spuId);

        return Result.ok(spuSaleAttrList);
    }


    /**
     * admin/product/spuImageList/{spuId}
     * 根据spuId 获取spuImage 集合
     * @param spuId
     * @return
     */
    @GetMapping("/spuImageList/{spuId}")
    public Result spuImageList(@PathVariable Long spuId){


        List<SpuImage> spuImageList=managerService.spuImageList(spuId);

        return Result.ok(spuImageList);

    }


}
