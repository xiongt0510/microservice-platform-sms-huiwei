package com.anjuxing.platform.sms.huiwei.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xiongt
 * @Description
 */
@ConfigurationProperties(prefix = "sms")
public class SmsProperties {

    private ClientProperties client = new ClientProperties();

    public ClientProperties getClient() {
        return client;
    }

    public void setClient(ClientProperties client) {
        this.client = client;
    }
}
