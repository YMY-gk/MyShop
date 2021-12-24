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
 * 
 * </p>
 *
 * @author guokui
 * @since 2021-12-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_point_log")
public class TbPointLog extends Model<TbPointLog> {

    private static final long serialVersionUID = 1L;

    @TableId("order_id")
    private String orderId;

    @TableField("user_id")
    private String userId;

    @TableField("point")
    private Integer point;


    @Override
    protected Serializable pkVal() {
        return this.orderId;
    }

}
