package wang.tyrael.sms;

import wang.tyrael.os.baidu.BosConfig;

public class SmsConfig {
    public final String accessKeyId;
    public final String secretAccessKey;
    public final String endPoint = "http://sms.bj.baidubce.com";
    public String invokeId;
    public String templateCode;

    public SmsConfig(String accessKeyId, String secretAccessKey) {
        this.accessKeyId = accessKeyId;
        this.secretAccessKey = secretAccessKey;
    }

    public SmsConfig(BosConfig bosConfig) {
        this.accessKeyId = bosConfig.ACCESS_KEY_ID;
        this.secretAccessKey = bosConfig.SECRET_ACCESS_KEY;
    }
}
