package com.me.user.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
@TableName("tb_stock_back")
public class TbStockBack extends Model<TbStockBack> {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @TableId("order_id")
    private String orderId;

    /**
     * SKU的id
     */
    @TableField("sku_id")
    private String skuId;

    /**
     * 回滚数量
     */
    @TableField("num")
    private Integer num;

    /**
     * 回滚状态
     */
    @TableField("status")
    private String status;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 回滚时间
     */
    @TableField("back_time")
    private LocalDateTime backTime;


    @Override
    protected Serializable pkVal() {
        return this.orderId;
    }

}
