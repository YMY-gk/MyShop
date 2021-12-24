package com.me.user.pojo;

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
 * 行政区域县区信息表
 * </p>
 *
 * @author guokui
 * @since 2021-12-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_areas")
public class TbAreas extends Model<TbAreas> {

    private static final long serialVersionUID = 1L;

    /**
     * 区域ID
     */
    @TableId("areaid")
    private String areaid;

    /**
     * 区域名称
     */
    @TableField("area")
    private String area;

    /**
     * 城市ID
     */
    @TableField("cityid")
    private String cityid;


    @Override
    protected Serializable pkVal() {
        return this.areaid;
    }

}
