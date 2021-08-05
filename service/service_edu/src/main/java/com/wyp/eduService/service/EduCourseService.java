package com.wyp.eduService.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyp.eduService.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wyp.eduService.entity.frontvo.CourseFrontVo;
import com.wyp.eduService.entity.frontvo.CourseWebVo;
import com.wyp.eduService.entity.vo.CourseInfoVo;
import com.wyp.eduService.entity.vo.CoursePublishVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-07-15
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    //根据课程id查询课程确认信息
    CoursePublishVo publishCourseInfo(String id);

    Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);

    CourseWebVo getBaseCourseInfo(String id);


}
