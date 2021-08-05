package com.wyp.vod.controller;

import com.wyp.commonutils.R;
import com.wyp.vod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("eduVod/video")
public class VodController {

    @Autowired
    private VodService vodService;

    //上传视频到阿里云
    @PostMapping("uploadAlyiVideo")
    public R uploadAlyiVideo(MultipartFile file) {
        System.out.println(12312);
        //返回上传视频id
        String videoId = vodService.uploadVideoAly(file);
        System.out.println(videoId);
        return R.ok().data("videoId",videoId);
    }

    //删除小节同时删除阿里云中存储的视频
    @DeleteMapping("{id}")
    public R deleteById(@PathVariable String id){
        vodService.deleteById(id);
        return R.ok();
    }

    //删除章节同时删除多个视频
    @DeleteMapping("deleteVideos")
    public R deleteIds(@RequestParam("videoList") List<String> videoList){

        vodService.deleteMoreAlyVideo(videoList);

        return R.ok();
    }


}
