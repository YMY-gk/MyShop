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
 * 
 * </p>
 *
 * @author guokui
 * @since 2021-10-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("undo_log")
public class UndoLog extends Model<UndoLog> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("branch_id")
    private Long branchId;

    @TableField("xid")
    private String xid;

    @TableField("rollback_info")
    private String rollbackInfo;

    @TableField("log_status")
    private Integer logStatus;

    @TableField("log_created")
    private Long logCreated;

    @TableField("log_modified")
    private Long logModified;

    @TableField("ext")
    private String ext;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
