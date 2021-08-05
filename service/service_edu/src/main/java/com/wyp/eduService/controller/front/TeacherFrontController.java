package com.wyp.eduService.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyp.commonutils.R;
import com.wyp.eduService.entity.EduCourse;
import com.wyp.eduService.entity.EduTeacher;
import com.wyp.eduService.service.EduCourseService;
import com.wyp.eduService.service.EduTeacherService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduService/teacherfront")
@CrossOrigin
public class TeacherFrontController {

    @Resource
    private EduTeacherService eduTeacherService;

    @Resource
    private EduCourseService eduCourseService;


    //获取讲师列表
    @GetMapping("getTeacherFrontList/{page}/{limit}")
    public R getTeacherFrontList(@PathVariable Long page,@PathVariable Long limit){
        Page<EduTeacher> teacherPage = new Page<>(page,limit);
        Map<String,Object> map = eduTeacherService.getTeacherFrontList(teacherPage);
        return R.ok().data(map);
    }

    // 根据id获取讲师的详细信信息
    @GetMapping("getTeacherFrontInfo/{teacherId}")
    public R selectTeacherById(@PathVariable String teacherId){
        EduTeacher teacher = eduTeacherService.getById(teacherId);
        QueryWrapper<EduCourse> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.eq("teacher_id",teacherId);
        List<EduCourse> courseList = eduCourseService.list(courseQueryWrapper);
        return  R.ok().data("teacher",teacher).data("courseList",courseList);
    }

}
