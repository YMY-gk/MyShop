package com.me.content.pojo;

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
 * @since 2021-10-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_content")
public class TbContent extends Model<TbContent> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 内容目录id
     */
    @TableField("category_id")
    private Long categoryId;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 链接
     */
    @TableField("url")
    private String url;

    /**
     * 图片路径
     */
    @TableField("pic")
    private String pic;

    /**
     * 状态
     */
    @TableField("status")
    private Integer status;

    /**
     * 排序
     */
    @TableField("sortOrder")
    private Integer sortorder;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
