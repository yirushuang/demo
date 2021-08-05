package com.wyp.vod.test;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.wyp.servicebase.exception.MyException;
import com.wyp.vod.utils.AliyunVodSDKUtils;
import com.wyp.vod.utils.ConstantVodUtils;

public class ceshi2 {

    public static void main(String[] args) {
        try {
            //初始化对象
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient("LTAI5tAYZSVmXU2jybYWbfQV", "2kh5Pi31uGWQU0K1G89X3Gc7DWCHmZ");
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();

            //videoIdList值转换成 1,2,3
            String videoIds = "86226d62d07346fb96995df70e70cb7d";

            //向request设置视频id
            request.setVideoIds(videoIds);
            //调用初始化对象的方法实现删除
            client.getAcsResponse(request);
        }catch(Exception e) {
            e.printStackTrace();
            throw new MyException(20001,"删除视频失败");
        }
    }

}
