package com.wyp.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantPropertiesUtils implements InitializingBean {

    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.file.keyId}")
    private String keyId;
    @Value("${aliyun.oss.file.keySecret}")
    private String keySecret;
    @Value("${aliyun.oss.file.bucketName}")
    private String bucketName;

    public static String KEY_ID;
    public static String ENDPOINT;
    public static String KEY_SECRET;
    public static String BUCKET_NAME;



    @Override
    public void afterPropertiesSet() throws Exception {
        KEY_ID = keyId;
        ENDPOINT = endpoint;
        KEY_SECRET = keySecret;
        BUCKET_NAME = bucketName;
    }
}
