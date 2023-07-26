import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sherry.taobao.gmall.common.result.Result;
import sherry.taobao.gmall.model.product.BaseCategory2;
import sherry.taobao.gmall.product.service.ManageService;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

/**
 * @Description:
 * @Author: SHERRY
 * @email: <a href="mailto:SherryTh743779@gmail.com">TianHai</a>
 * @Date: 2023/7/28 15:52
 */
public class test {
    @Autowired
    private ManageService manageService;
    public static void main(String[] args)  {

    }
    @GetMapping("getCategory2/{category1Id}")
    public Result<List<BaseCategory2>> getCategory2(@PathVariable("category1Id") Long category1Id) {
        List<BaseCategory2> baseCategory2List = manageService.getCategory2(category1Id);
        return Result.ok(baseCategory2List);
    }

}
