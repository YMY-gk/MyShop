package com.me.user.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@TableName("oauth_approvals")
public class OauthApprovals extends Model<OauthApprovals> {

    private static final long serialVersionUID = 1L;

    @TableField("userId")
    private String userid;

    @TableField("clientId")
    private String clientid;

    @TableField("scope")
    private String scope;

    @TableField("status")
    private String status;

    @TableField("expiresAt")
    private LocalDateTime expiresat;

    @TableField("lastModifiedAt")
    private LocalDateTime lastmodifiedat;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
