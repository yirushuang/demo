package com.wyp.eduService.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyp.eduService.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-07-08
 */
public interface EduTeacherService extends IService<EduTeacher> {
    //首页查询讲师的方法
    Map<String, Object> getTeacherFrontList(Page<EduTeacher> teacherPage);
}
