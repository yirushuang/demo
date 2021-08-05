package com.wyp.eduService.service;

import com.wyp.eduService.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wyp.eduService.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-07-14
 */
public interface EduSubjectService extends IService<EduSubject> {
    //excel 添加课程分类
    public void saveSubject(MultipartFile file, EduSubjectService subjectService);
    // 查询分类列表并封装数据
    public List<OneSubject> subjectList();
}
