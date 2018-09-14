package com.anjuxing.platform.sms.huiwei.controller;

import com.anjuxing.platform.sms.huiwei.model.SendResult;
import com.anjuxing.platform.sms.huiwei.model.SmsID;
import com.anjuxing.platform.sms.huiwei.service.SmsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author xiongt
 * @Description
 */
@RestController
@RequestMapping("/huawei/sms")
public class SmsController {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private SmsService smsService;

    @Autowired
    private ObjectMapper mapper;



    @GetMapping("/send")
    public <T> T send(@RequestParam("mobiles") String mobiles) throws Exception {



        SendResult sendResult = smsService.send(mobiles);

        /** 如果没有SmsID的信息返回，就返回当前错误码与信息 */
        if (Objects.isNull(sendResult.getResult()) ){
            return (T)sendResult;
        }

        List<SmsID> smsIDS = new ArrayList<>();
        /** 如果有smsId 的信息返回，就返回 SmsId 的集合信息 */
        for (Object object : sendResult.getResult()){
            smsIDS.add(mapper.convertValue(object,SmsID.class));
        }
        return (T)smsIDS;
    }


}
