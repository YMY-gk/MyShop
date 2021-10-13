package com.me.goods.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author guokui
 * @since 2021-10-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_sku")
public class TbSku extends Model<TbSku> {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    @TableId("id")
    private Long id;

    /**
     * 商品条码
     */
    @TableField("sn")
    private String sn;

    /**
     * SKU名称
     */
    @TableField("name")
    private String name;

    /**
     * 价格（分）
     */
    @TableField("price")
    private Integer price;

    /**
     * 库存数量
     */
    @TableField("num")
    private Integer num;

    /**
     * 库存预警数量
     */
    @TableField("alert_num")
    private Integer alertNum;

    /**
     * 商品图片
     */
    @TableField("image")
    private String image;

    /**
     * 商品图片列表
     */
    @TableField("images")
    private String images;

    /**
     * 重量（克）
     */
    @TableField("weight")
    private Integer weight;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * SPUID
     */
    @TableField("spu_id")
    private String spuId;

    /**
     * 类目ID
     */
    @TableField("category_id")
    private Integer categoryId;

    /**
     * 类目名称
     */
    @TableField("category_name")
    private String categoryName;

    /**
     * 品牌名称
     */
    @TableField("brand_name")
    private String brandName;

    /**
     * 规格
     */
    @TableField("spec")
    private String spec;

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
     * 商品状态 1-正常，2-下架，3-删除
     */
    @TableField("status")
    private String status;

    @TableField("version")
    private Integer version;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
