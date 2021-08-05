package com.wyp.eduService.controller;


import com.wyp.commonutils.R;
import com.wyp.eduService.entity.EduChapter;
import com.wyp.eduService.entity.chapter.ChapterVo;
import com.wyp.eduService.service.EduChapterService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-07-15
 */
@RestController
@CrossOrigin
@RequestMapping("/eduService/chapter")
public class EduChapterController {

    @Resource
    private EduChapterService eduChapterService;

    //根据课程ID查询所有的章节和小节，并封装返回
    @GetMapping("{id}")
    public R getChapterVideo(@PathVariable String id){

        List<ChapterVo> chapterVideo = eduChapterService.getChapterVideo(id);
        return R.ok().data("chapterVideo",chapterVideo);
    }

    //添加章节
    @PostMapping("/addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter){
        boolean save = eduChapterService.save(eduChapter);
        if(save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //删除章节
    @DeleteMapping("{id}")
    public R deleteChapter(@PathVariable String id){
        boolean b = eduChapterService.deleteChapter(id);
        if(b){
            return R.ok();
        }else{
            return R.error().message("该章节下存在其他小节，无法删除");
        }
    }
    //根据id查询一个章节
    @GetMapping("selectById/{id}")
    public R selectById(@PathVariable String id){
        EduChapter chapter = eduChapterService.getById(id);
        return R.ok().data("chapter",chapter);
    }
    //修改章节
    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter chapter){
        eduChapterService.updateById(chapter);
        return R.ok();
    }
}

