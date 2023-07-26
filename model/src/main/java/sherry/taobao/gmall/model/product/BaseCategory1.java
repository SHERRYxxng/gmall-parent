package sherry.taobao.gmall.model.product;

import sherry.taobao.gmall.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "商品一级分类")//在APIModel中详情为一级分类
@TableName("base_category1")//指定数据库中的表名
public class BaseCategory1 extends BaseEntity {
    //此表中有一个属性为大分类,比如每一个都是大类
    // 图书、音像、电子书刊
    //手机
    //家用电器
    //数码
    //家居家装
    //电脑办公
    //厨具
    //个护化妆
    //服饰内衣
    //钟表
    //鞋靴
    //母婴
    //礼品箱包
    //食品饮料、保健食品
    //珠宝
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分类名称")
    @TableField("name")
    private String name;

}
