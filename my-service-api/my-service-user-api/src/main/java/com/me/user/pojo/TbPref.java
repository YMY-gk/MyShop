package com.me.user.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;
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
@TableName("tb_pref")
public class TbPref extends Model<TbPref> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 分类ID
     */
    @TableField("cate_id")
    private Integer cateId;

    /**
     * 消费金额
     */
    @TableField("buy_money")
    private Integer buyMoney;

    /**
     * 优惠金额
     */
    @TableField("pre_money")
    private Integer preMoney;

    /**
     * 活动开始日期
     */
    @TableField("start_time")
    private LocalDate startTime;

    /**
     * 活动截至日期
     */
    @TableField("end_time")
    private LocalDate endTime;

    /**
     * 类型,1:普通订单，2：限时活动
     */
    @TableField("type")
    private String type;

    /**
     * 状态,1:有效，0：无效
     */
    @TableField("state")
    private String state;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
