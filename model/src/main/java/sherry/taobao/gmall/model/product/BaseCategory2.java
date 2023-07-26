package sherry.taobao.gmall.model.product;

import sherry.taobao.gmall.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "商品二级分类")
@TableName("base_category2")
public class BaseCategory2 extends BaseEntity {
    //此表中有2个属性一个为一级分类一个为二级分类,通过join on 让两个相同字段进行连接这里连接为表1的id和表二的category1_id
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "二级分类名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "一级分类编号")
    @TableField("category1_id")
    private Long category1Id;

}
