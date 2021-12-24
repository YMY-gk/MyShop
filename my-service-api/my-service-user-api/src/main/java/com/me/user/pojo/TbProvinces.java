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
 * 省份信息表
 * </p>
 *
 * @author guokui
 * @since 2021-12-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_provinces")
public class TbProvinces extends Model<TbProvinces> {

    private static final long serialVersionUID = 1L;

    /**
     * 省份ID
     */
    @TableId("provinceid")
    private String provinceid;

    /**
     * 省份名称
     */
    @TableField("province")
    private String province;


    @Override
    protected Serializable pkVal() {
        return this.provinceid;
    }

}
