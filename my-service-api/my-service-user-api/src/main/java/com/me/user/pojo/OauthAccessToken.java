package com.me.user.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("oauth_access_token")
public class OauthAccessToken extends Model<OauthAccessToken> {

    private static final long serialVersionUID = 1L;

    @TableField("token_id")
    private String tokenId;

    @TableField("token")
    private Blob token;

    @TableId("authentication_id")
    private String authenticationId;

    @TableField("user_name")
    private String userName;

    @TableField("client_id")
    private String clientId;

    @TableField("authentication")
    private Blob authentication;

    @TableField("refresh_token")
    private String refreshToken;


    @Override
    protected Serializable pkVal() {
        return this.authenticationId;
    }

}
