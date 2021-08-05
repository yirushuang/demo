package com.wyp.oss.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface OssService {

    //上传视频
    String uploadFileAvatar(MultipartFile multipartFile);

    /**
     * 删除章节时删除章节下所有视频
     */
    void deleteVideo(List videos);

}
