package sherry.taobao.gmall.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import sherry.taobao.gmall.model.product.BaseTrademark;

/**
 * @Description:
 * @Author: SHERRY
 * @email: <a href="mailto:SherryTh743779@gmail.com">TianHai</a>
 * @Date: 2023/7/28 19:46
 */
public interface BaseTrademarkService  extends IService<BaseTrademark> {

    /**
     * Banner分页列表
     * @param pageParam
     * @return
     */
    IPage<BaseTrademark> getPage(Page<BaseTrademark> pageParam);

}
