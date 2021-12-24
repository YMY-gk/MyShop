package com.me.user.pojo;

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
 * @since 2021-12-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_address")
public class TbAddress extends Model<TbAddress> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 省
     */
    @TableField("provinceid")
    private String provinceid;

    /**
     * 市
     */
    @TableField("cityid")
    private String cityid;

    /**
     * 县/区
     */
    @TableField("areaid")
    private String areaid;

    /**
     * 电话
     */
    @TableField("phone")
    private String phone;

    /**
     * 详细地址
     */
    @TableField("address")
    private String address;

    /**
     * 联系人
     */
    @TableField("contact")
    private String contact;

    /**
     * 是否是默认 1默认 0否
     */
    @TableField("is_default")
    private String isDefault;

    /**
     * 别名
     */
    @TableField("alias")
    private String alias;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
