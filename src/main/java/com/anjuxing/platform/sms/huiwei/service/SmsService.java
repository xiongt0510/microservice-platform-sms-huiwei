package com.anjuxing.platform.sms.huiwei.service;

import com.anjuxing.platform.sms.huiwei.model.SendResult;


/**
 * @author xiongt
 * @Description
 */
public interface SmsService {


    /**
     * 发送短信接口
     * @param toMobiles  发送到的目标手机号,多个手机号用 , 隔开
     *                   如：“13112345678，13501230000”
     * @return 远程调用所返回 的json 字符串
     */
    SendResult send(String toMobiles) throws Exception;


}
