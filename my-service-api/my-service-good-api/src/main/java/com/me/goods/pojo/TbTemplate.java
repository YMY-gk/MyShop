package com.baomidou.entity;

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
 * 
 * </p>
 *
 * @author guokui
 * @since 2021-10-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_template")
public class TbTemplate extends Model<TbTemplate> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 模板名称
     */
    @TableField("name")
    private String name;

    /**
     * 规格数量
     */
    @TableField("spec_num")
    private Integer specNum;

    /**
     * 参数数量
     */
    @TableField("para_num")
    private Integer paraNum;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
