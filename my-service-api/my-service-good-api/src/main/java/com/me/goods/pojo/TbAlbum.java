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
 * 相册表
 * </p>
 *
 * @author guokui
 * @since 2021-10-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_album")
public class TbAlbum extends Model<TbAlbum> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 相册名称
     */
    @TableField("title")
    private String title;

    /**
     * 相册封面
     */
    @TableField("image")
    private String image;

    /**
     * 图片列表
     */
    @TableField("image_items")
    private String imageItems;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
