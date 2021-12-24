package com.me.user.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.sql.Blob;
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
@TableName("oauth_refresh_token")
public class OauthRefreshToken extends Model<OauthRefreshToken> {

    private static final long serialVersionUID = 1L;

    @TableField("token_id")
    private String tokenId;

    @TableField("token")
    private Blob token;

    @TableField("authentication")
    private Blob authentication;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
