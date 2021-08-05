package com.wyp.oss.controller;


import com.wyp.commonutils.R;
import com.wyp.oss.service.OssService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.ws.rs.PathParam;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("eduOss/fileOss")
public class OssController {

    @Resource
    private OssService ossService;

    @PostMapping
    public R uploadFile(MultipartFile file){

        String url = ossService.uploadFileAvatar(file);
        return R.ok().data("url",url);
    }

    @DeleteMapping("deleteVideos")
    public R deleteVideos(@RequestParam("videos") List videos){

        //TODO


        return R.ok();
    }

}
