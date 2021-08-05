package com.wyp.vod.test;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.wyp.vod.utils.AliyunVodSDKUtils;

public class ceshi {

    public static void main(String[] args) throws ClientException {
        String accessKeyId = "LTAI5tAYZSVmXU2jybYWbfQV";
        String accessKeySecret = "2kh5Pi31uGWQU0K1G89X3Gc7DWCHmZ";


        //初始化客户端、请求对象和相应对象
        DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(accessKeyId,accessKeySecret);
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        try {
            //设置请求参数
            request.setVideoId("42e0ed1910db46ac9745849cb8a18a80");
            //获取请求响应
            response = client.getAcsResponse(request);
            //输出请求结果
            //播放凭证
            System.out.print("PlayAuth = " + response.getPlayAuth() + "\n");
            //VideoMeta信息
            System.out.print("VideoMeta.Title = " + response.getVideoMeta().getTitle() + "\n");
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }

}
