package com.me.goods.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品类目
 * </p>
 *
 * @author guokui
 * @since 2021-10-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_category")
public class TbCategory extends Model<TbCategory> {

    private static final long serialVersionUID = 1L;

    /**
     * 分类ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 分类名称
     */
    @TableField("name")
    private String name;

    /**
     * 商品数量
     */
    @TableField("goods_num")
    private Integer goodsNum;

    /**
     * 是否显示
     */
    @TableField("is_show")
    private String isShow;

    /**
     * 是否导航
     */
    @TableField("is_menu")
    private String isMenu;

    /**
     * 排序
     */
    @TableField("seq")
    private Integer seq;

    /**
     * 上级ID
     */
    @TableField("parent_id")
    private Integer parentId;

    /**
     * 模板ID
     */
    @TableField("template_id")
    private Integer templateId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
