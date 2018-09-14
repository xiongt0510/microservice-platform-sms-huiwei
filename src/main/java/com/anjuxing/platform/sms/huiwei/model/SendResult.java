package com.anjuxing.platform.sms.huiwei.model;

/**
 * @author xiongt
 * @Description 接收短信发送返回的结果
 */
public class SendResult {

    private String code;

    private String description;

    private Object [] result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object[] getResult() {
        return result;
    }

    public void setResult(Object[] result) {
        this.result = result;
    }
}
