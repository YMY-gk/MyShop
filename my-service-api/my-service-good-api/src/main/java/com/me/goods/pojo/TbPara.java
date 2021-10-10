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
@TableName("tb_para")
public class TbPara extends Model<TbPara> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 选项
     */
    @TableField("options")
    private String options;

    /**
     * 排序
     */
    @TableField("seq")
    private Integer seq;

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
