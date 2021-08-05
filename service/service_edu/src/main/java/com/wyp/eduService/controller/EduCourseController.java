package com.wyp.eduService.controller;


import com.wyp.commonutils.R;
import com.wyp.eduService.entity.EduCourse;
import com.wyp.eduService.entity.vo.CourseInfoVo;
import com.wyp.eduService.entity.vo.CoursePublishVo;
import com.wyp.eduService.service.EduCourseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
@RequestMapping("/eduService/course")
public class EduCourseController {

    @Resource
    private EduCourseService eduCourseService;

    @PostMapping("/addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfo){
        String courseId = eduCourseService.saveCourseInfo(courseInfo);
        return R.ok().data("courseId",courseId);
    }

    @GetMapping("{id}")
    public R selectById(@PathVariable String id){
        EduCourse course = eduCourseService.getById(id);
        return R.ok().data("course",course);
    }

    @PostMapping("/updateCourse")
    public R updateCourse(@RequestBody CourseInfoVo courseInfo){

        // TODO



        return R.ok();
    }

    //根据课程id查询课程确认信息
    @GetMapping("getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(@PathVariable String id) {
        CoursePublishVo coursePublishVo = eduCourseService.publishCourseInfo(id);
        return R.ok().data("publishCourse",coursePublishVo);
    }


}

