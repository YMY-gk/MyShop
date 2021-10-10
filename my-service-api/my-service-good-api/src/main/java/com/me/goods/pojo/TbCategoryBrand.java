package com.baomidou.entity;

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
 * @since 2021-10-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_category_brand")
public class TbCategoryBrand extends Model<TbCategoryBrand> {

    private static final long serialVersionUID = 1L;

    /**
     * 分类ID
     */
    @TableId("category_id")
    private Integer categoryId;

    /**
     * 品牌ID
     */
    @TableField("brand_id")
    private Integer brandId;


    @Override
    protected Serializable pkVal() {
        return this.categoryId;
    }

}
