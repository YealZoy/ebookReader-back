package com.bksx.ebookreader.bean;

import lombok.Data;

/**
 * 微信引导用户参数
 */
@Data
public class WechatInfo {
    private String appid;
    private String redirect_uri;
    private String response_type;
    private String scope;
    private String state;
    private String wechat_redirect;
}
