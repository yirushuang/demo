package com.wyp.eduService.controller;


import com.wyp.commonutils.R;
import com.wyp.eduService.entity.EduVideo;
import com.wyp.eduService.service.EduVideoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-07-15
 */
@RestController
@CrossOrigin
@RequestMapping("/eduService/video")
public class EduVideoController {

    @Resource
    private EduVideoService eduVideoService;

    /**
     * 根据Id删除一个小节
     * @return
     */
    @DeleteMapping("{id}")
    public R deleteById(@PathVariable String id){
        eduVideoService.removeById(id);
        return R.ok();
    }

    /**
     * 添加一个小节
     */
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo video){
        eduVideoService.save(video);
        return R.ok();
    }

    /**
     * 根据Id查询一个小节
     * @return
     */
    @GetMapping("{id}")
    public R selectById(@PathVariable String id){
        EduVideo video = eduVideoService.getById(id);
        return R.ok().data("video",video);
    }

    /**
     * 修改一个小节
     */
    @PostMapping("updateVideo")
    public R updateVideo(@RequestBody EduVideo video){
        eduVideoService.updateById(video);
        return R.ok();
    }

}

