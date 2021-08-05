package com.wyp.eduService.controller.front;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyp.commonutils.R;
import com.wyp.eduService.entity.EduChapter;
import com.wyp.eduService.entity.EduCourse;
import com.wyp.eduService.entity.chapter.ChapterVo;
import com.wyp.eduService.entity.frontvo.CourseFrontVo;
import com.wyp.eduService.entity.frontvo.CourseWebVo;
import com.wyp.eduService.service.EduChapterService;
import com.wyp.eduService.service.EduCourseService;
import com.wyp.eduService.service.EduTeacherService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduService/coursefront")
@CrossOrigin
public class CourseFrontController {

    @Resource
    private EduCourseService eduCourseService;
    @Resource
    private EduTeacherService teacherService;
    @Resource
    private EduChapterService eduChapterService;

    @GetMapping("getFrontCourseInfo/{id}")
    public R getFrontCourseInfo(@PathVariable String id){
        //根据课程id，编写sql语句查询课程信息
        CourseWebVo courseWebVo = eduCourseService.getBaseCourseInfo(id);
        //根据课程id查询章节和小节
        List<ChapterVo> chapterVideoList = eduChapterService.getChapterVideo(id);
        return R.ok().data("courseWebVo",courseWebVo).data("chapterVideoList",chapterVideoList);

    }

    //条件查询+分页查询
    @PostMapping("getFrontCourseList/{page}/{limit}")
    public R getFrontCourseController(@PathVariable long page,
                                      @PathVariable long limit,
                                      @RequestBody(required = false) CourseFrontVo courseFrontVo){
        Page<EduCourse> pageCourse = new Page<>(page,limit);
        Map<String,Object> map = eduCourseService.getCourseFrontList(pageCourse,courseFrontVo);

        return R.ok().data(map);
    }


}
