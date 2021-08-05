package com.wyp.oss.service.ipml;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.wyp.oss.service.OssService;
import com.wyp.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Service
public class OssServiceImpl  implements OssService {

    @Override
    public String uploadFileAvatar(MultipartFile multipartFile) {

        String name = multipartFile.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        name = new DateTime().toString("yyyy/MM")+uuid+name;
        String endpoint = ConstantPropertiesUtils.ENDPOINT;
        String accessKeyId = ConstantPropertiesUtils.KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;
        //1.创建Oss实例
        OSS oss = new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);
        try {
            InputStream is = multipartFile.getInputStream();
            oss.putObject(bucketName,name,is);
            String url = "http://"+bucketName+"."+endpoint+"/"+name;
            return url;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            oss.shutdown();
        }
    }

    @Override
    public void deleteVideo(List videos) {




    }
}
