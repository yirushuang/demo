package com.wyp.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyp.eduService.entity.EduCourse;
import com.wyp.eduService.entity.EduCourseDescription;
import com.wyp.eduService.entity.frontvo.CourseFrontVo;
import com.wyp.eduService.entity.frontvo.CourseWebVo;
import com.wyp.eduService.entity.vo.CourseInfoVo;
import com.wyp.eduService.entity.vo.CoursePublishVo;
import com.wyp.eduService.mapper.EduCourseMapper;
import com.wyp.eduService.service.EduCourseDescriptionService;
import com.wyp.eduService.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-07-15
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService   {

    //引入课程说明业务类
    @Resource
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //保存课程
        EduCourse course = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,course);
        baseMapper.insert(course);
        String id = course.getId();
        //保存课程描述
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(id);
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescriptionService.save(eduCourseDescription);
        return course.getId();
    }

    @Override
    public CoursePublishVo publishCourseInfo(String id) {
        CoursePublishVo coursePublishVo = baseMapper.getPublishCourseInfo(id);
        return coursePublishVo;
    }

    @Override
    public Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(courseFrontVo.getSubjectParentId())) { //一级分类
            wrapper.eq("subject_parent_id",courseFrontVo.getSubjectParentId());
        }
        if(!StringUtils.isEmpty(courseFrontVo.getSubjectId())) { //二级分类
            wrapper.eq("subject_id",courseFrontVo.getSubjectId());
        }
        if(!StringUtils.isEmpty(courseFrontVo.getBuyCountSort())) { //关注度
            wrapper.orderByDesc("buy_count");
        }
        if (!StringUtils.isEmpty(courseFrontVo.getGmtCreateSort())) { //最新
            wrapper.orderByDesc("gmt_create");
        }

        if (!StringUtils.isEmpty(courseFrontVo.getPriceSort())) {//价格
            wrapper.orderByDesc("price");
        }

        baseMapper.selectPage(pageCourse,wrapper);

        List<EduCourse> records = pageCourse.getRecords();
        long current = pageCourse.getCurrent();
        long pages = pageCourse.getPages();
        long size = pageCourse.getSize();
        long total = pageCourse.getTotal();
        boolean hasNext = pageCourse.hasNext();//下一页
        boolean hasPrevious = pageCourse.hasPrevious();//上一页

        //把分页数据获取出来，放到map集合
        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
        //map返回
        return map;

    }

    @Override
    public CourseWebVo getBaseCourseInfo(String id) {
        CourseWebVo courseWebVo = baseMapper.getBaseCourseInfo(id);
        return courseWebVo;
    }
}
