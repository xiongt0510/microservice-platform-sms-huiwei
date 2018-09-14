package com.anjuxing.platform.sms.huiwei.properties;

/**
 * @author xiongt
 * @Description
 */
public class ClientProperties {

    //开发准备：APP接入地址 + 接口访问URI
    String url = "https://api.rtc.huaweicloud.com:10443/sms/batchSendDiffSms/v1";
    //开发准备：APP_Key
    String appKey = "";
    //开发准备：APP_Secret
    String appSecret = "";

    //开发准备：签名通道号
    String sender = "1069100121280333";
    //填写短信接收人号码，多个号码之间用英文逗号分隔
//    String receiver = "";
    //状态报告接收地址，为空或者不填表示不接收状态报告
    String statusCallBack = "";

    //开发准备：模板ID
    String templateId = "3faf8c01e6ad40929ed92837a40b8150";
    //模板变量请务必根据实际情况修改，查看更多模板变量规则
    //如模板内容为“您有${NUM_2}件快递请到${TXT_32}领取”时，templateParas可填写为[\"3\",\"人民公园正门\"]
    //双变量示例：String templateParas = "[\"3\",\"人民公园正门\"]";
    String templateParas = "[\"369751\"]";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }



    public String getStatusCallBack() {
        return statusCallBack;
    }

    public void setStatusCallBack(String statusCallBack) {
        this.statusCallBack = statusCallBack;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTemplateParas() {
        return templateParas;
    }

    public void setTemplateParas(String templateParas) {
        this.templateParas = templateParas;
    }
}
