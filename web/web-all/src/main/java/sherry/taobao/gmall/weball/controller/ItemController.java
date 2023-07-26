package sherry.taobao.gmall.weball.controller;

import sherry.taobao.gmall.common.result.Result;
import sherry.taobao.gmall.item.client.ItemFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Map;

@Controller
public class ItemController {

    @Resource
    private ItemFeignClient itemFeignClient;

    /**
     * 详情页渲染
     * @param skuId
     * @param model
     * @return
     */
    @GetMapping("/{skuId}.html")
    public  String item(@PathVariable Long skuId, Model model){
        //获取详情数据
        Result<Map<String,Object>> result = itemFeignClient.item(skuId);
        model.addAllAttributes(result.getData());
        return "item/item";
    }

}