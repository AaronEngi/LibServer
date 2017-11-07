package com.shuitianyun.sms;

import wang.tyrael.os.baidu.BosConfig;

public class SmsConfig {
    public final String accessKeyId;
    public final String secretAccessKey;
    public final String endPoint = "http://sms.bj.baidubce.com";
    public final String invokeId = "dkw86m01T-26Nv9-H5Z3";
    public final String templateCode = "smsTpl:0000000000000000000000";

    public SmsConfig(String accessKeyId, String secretAccessKey) {
        this.accessKeyId = accessKeyId;
        this.secretAccessKey = secretAccessKey;
    }

    public SmsConfig(BosConfig bosConfig) {
        this.accessKeyId = bosConfig.ACCESS_KEY_ID;
        this.secretAccessKey = bosConfig.SECRET_ACCESS_KEY;
    }
}
