package com.wyp.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VodService {
    //上传视频到阿里云
    String uploadVideoAly(MultipartFile file);

    //删除一条视频
    void deleteById(String id);

    void deleteMoreAlyVideo(List<String> videoList);
}
