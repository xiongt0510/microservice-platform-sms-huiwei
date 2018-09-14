package com.anjuxing.platform.sms.huiwei.service;

import com.anjuxing.platform.sms.huiwei.model.SendResult;
import com.anjuxing.platform.sms.huiwei.properties.SmsProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author xiongt
 * @Description
 */
@Service
public class SmsServiceImpl implements SmsService {

    //用于格式化鉴权头域，给“X-WSSE”参数赋值
    private static final String WSSE_HEADER_FORMAT = "UsernameToken Username=\"%s\",PasswordDigest=\"%s\",Nonce=\"%s\",Created=\"%s\"";
    //用于格式化鉴权头域，给“Authorization”参数赋值
    private static final String AUTH_HEADER_VALUE = "WSSE realm=\"SDP\",profile=\"UsernameToken\",type=\"Appkey\"";

    @Autowired
    private SmsProperties sms;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public SendResult send(String toMobiles) throws Exception {

        //请求Headers中的X-WSSE参数值
        String wsseHeader = buildWsseHeader(sms.getClient().getAppKey(), sms.getClient().getAppSecret());

        //请求Body
        String body = buildRequestBody(
                sms.getClient().getSender(),
                toMobiles,
                sms.getClient().getTemplateId(),
                sms.getClient().getTemplateParas(),
                sms.getClient().getStatusCallBack());

        //构建http 客户端
        CloseableHttpClient client = HttpClients.custom()
                .setSSLContext(new SSLContextBuilder().loadTrustMaterial(null,
                        (x509CertChain, authType) -> true).build())
                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                .build();

        //发送请求并得到响应
        HttpResponse response = client.execute(RequestBuilder.create("POST")
                .setUri(sms.getClient().getUrl())
                .addHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
                .addHeader(HttpHeaders.AUTHORIZATION, AUTH_HEADER_VALUE)
                .addHeader("X-WSSE", wsseHeader)
                .setEntity(new StringEntity(body)).build());

        return mapper.readValue(EntityUtils.toString(response.getEntity()),SendResult.class);
    }


    /**
     * 构建参数传递
     * @param sender
     * @param receiver
     * @param templateId
     * @param templateParas
     * @param statusCallbackUrl
     * @return
     */
     private String buildRequestBody(String sender, String receiver, String templateId, String templateParas,
                                   String statusCallbackUrl) {

        List<NameValuePair> keyValues = new ArrayList<NameValuePair>();

        keyValues.add(new BasicNameValuePair("from", sender));
        keyValues.add(new BasicNameValuePair("to", receiver));
        keyValues.add(new BasicNameValuePair("templateId", templateId));
        keyValues.add(new BasicNameValuePair("templateParas", templateParas));
        keyValues.add(new BasicNameValuePair("statusCallback", statusCallbackUrl));

        return URLEncodedUtils.format(keyValues, StandardCharsets.UTF_8);
    }

    /**
     * 构建 header wsse 属性
     * @param appKey
     * @param appSecret
     * @return
     */
     private String buildWsseHeader(String appKey, String appSecret) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String time = sdf.format(new Date());
        String nonce = UUID.randomUUID().toString().replace("-", "");

        byte[] passwordDigest = DigestUtils.sha256(nonce + time + appSecret);
        String hexDigest = Hex.encodeHexString(passwordDigest);
        String passwordDigestBase64Str = Base64.encodeBase64String(hexDigest.getBytes(Charset.forName("utf-8")));
        return String.format(WSSE_HEADER_FORMAT, appKey, passwordDigestBase64Str, nonce, time);
    }
}
