package com.anjuxing.platform.sms.huiwei.model;

/**
 * @author xiongt
 * 短信ID列表，当目的号码存在多个时，每个号码都会返回一个SmsID。当返回异常响应时不携带此字段
 * @Description
 */
public class SmsID {

    /** 短信的唯一标识。 */
    private String smsMsgId;

    /** 短信发送方的号码。 */
    private String form ;

    /** 短信接收方的号码。 */
    private String originTo;

    /** 短信状态码
     * 000000：成功
     E200015：待发送短信数量太大
     E200028：模板校验失败
     E200029：模板匹配失败
     E200030：模板未激活
     E200031：协议校验失败
     E200033：模板类型不正确

     * */
    private String status;

    /** 短信资源的创建时间，即短信平台接收到SP发送短信请求的时间。

     格式为：YYYY-MM-DD'T'hh:mm:ss'Z'。 */
    private String createTime;

    public String getSmsMsgId() {
        return smsMsgId;
    }

    public void setSmsMsgId(String smsMsgId) {
        this.smsMsgId = smsMsgId;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getOriginTo() {
        return originTo;
    }

    public void setOriginTo(String originTo) {
        this.originTo = originTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
