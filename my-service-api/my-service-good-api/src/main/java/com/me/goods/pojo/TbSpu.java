package com.me.goods.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author guokui
 * @since 2021-10-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_spu")
public class TbSpu extends Model<TbSpu> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("id")
    private String id;

    /**
     * 货号
     */
    @TableField("sn")
    private String sn;

    /**
     * SPU名
     */
    @TableField("name")
    private String name;

    /**
     * 副标题
     */
    @TableField("caption")
    private String caption;

    /**
     * 品牌ID
     */
    @TableField("brand_id")
    private Integer brandId;

    /**
     * 一级分类
     */
    @TableField("category1_id")
    private Integer category1Id;

    /**
     * 二级分类
     */
    @TableField("category2_id")
    private Integer category2Id;

    /**
     * 三级分类
     */
    @TableField("category3_id")
    private Integer category3Id;

    /**
     * 模板ID
     */
    @TableField("template_id")
    private Integer templateId;

    /**
     * 运费模板id
     */
    @TableField("freight_id")
    private Integer freightId;

    /**
     * 图片
     */
    @TableField("image")
    private String image;

    /**
     * 图片列表
     */
    @TableField("images")
    private String images;

    /**
     * 售后服务
     */
    @TableField("sale_service")
    private String saleService;

    /**
     * 介绍
     */
    @TableField("introduction")
    private String introduction;

    /**
     * 规格列表
     */
    @TableField("spec_items")
    private String specItems;

    /**
     * 参数列表
     */
    @TableField("para_items")
    private String paraItems;

    /**
     * 销量
     */
    @TableField("sale_num")
    private Integer saleNum;

    /**
     * 评论数
     */
    @TableField("comment_num")
    private Integer commentNum;

    /**
     * 是否上架
     */
    @TableField("is_marketable")
    private String isMarketable;

    /**
     * 是否启用规格
     */
    @TableField("is_enable_spec")
    private String isEnableSpec;

    /**
     * 是否删除
     */
    @TableField("is_delete")
    private String isDelete;

    /**
     * 审核状态
     */
    @TableField("status")
    private String status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
