package com.bksx.ebookreader.bean;

import lombok.Data;

@Data
public class WechartToken {
    private  String access_token;
    private  String expires_in;
    private  String refresh_token;
    private  String  openid;
    private  String scope;
}
