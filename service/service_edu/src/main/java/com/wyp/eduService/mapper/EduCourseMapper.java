package com.wyp.eduService.mapper;

import com.wyp.eduService.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyp.eduService.entity.frontvo.CourseWebVo;
import com.wyp.eduService.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-07-15
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {


    public CoursePublishVo getPublishCourseInfo(String courseId);

    //根据课程ID 查询于该课程详细信息、所属讲师、所属分类
    CourseWebVo getBaseCourseInfo(String courseId);
}
