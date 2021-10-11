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
 * 品牌表
 * </p>
 *
 * @author guokui
 * @since 2021-10-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_brand")
public class TbBrand extends Model<TbBrand> {

    private static final long serialVersionUID = 1L;

    /**
     * 品牌id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 品牌名称
     */
    @TableField("name")
    private String name;

    /**
     * 品牌图片地址
     */
    @TableField("image")
    private String image;

    /**
     * 品牌的首字母
     */
    @TableField("letter")
    private String letter;

    /**
     * 排序
     */
    @TableField("seq")
    private Integer seq;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
